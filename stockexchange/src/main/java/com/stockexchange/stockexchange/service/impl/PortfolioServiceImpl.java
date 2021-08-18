package com.stockexchange.stockexchange.service.impl;

import com.stockexchange.stockexchange.model.Portfolio;
import com.stockexchange.stockexchange.model.Stock;
import com.stockexchange.stockexchange.repository.PortfolioRepository;
import com.stockexchange.stockexchange.repository.StockRepository;
import com.stockexchange.stockexchange.repository.UsersRepository;
import com.stockexchange.stockexchange.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);

    PortfolioRepository portfolioRepository;
    StockRepository stockRepository;
    UsersRepository usersRepository;


    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, StockRepository stockRepository, UsersRepository usersRepository) {
        this.portfolioRepository = portfolioRepository;
        this.stockRepository = stockRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Portfolio> getAllPortfolio(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }

    @Override
    public Portfolio sellStock(Long portfolioId, int amount) {
        try {
          Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
            if (0 < amount && amount <= portfolio.getAmount()) {
                Random random = new Random();
                int randomCost = random.nextInt(100);
                portfolio.setAmount(portfolio.getAmount()-amount);
                portfolio.setCost(randomCost);
                if(portfolio.getAmount()==0){
                    portfolioRepository.deletePortfolioByPortfolioId(portfolioId);
                }else {
                    portfolioRepository.save(portfolio);
                }
            }
            else System.out.println("not Enough stock");
            return portfolio;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }


    }

    @Override
    public Portfolio buyStock(Long stockId, Long userId, int amount) {
        Portfolio portfolio = null;
        Random random = new Random();
        int randomCost = random.nextInt(100);
        try {
            Optional<Stock> stock = stockRepository.findById(stockId);
            portfolio = portfolioRepository.findByUserIdAndStockId(userId,stockId);
            if (portfolio != null && portfolio.getUserId().equals(userId) && portfolio.getStockId().equals(stockId)) {
                int amount1 = amount + portfolio.getAmount();
                portfolio.setAmount(amount1);
                portfolio.setCost(randomCost);

            } else {

                portfolio = new Portfolio();
                portfolio.setAmount(amount);
                portfolio.setStockName(stock.get().getStockname());
                portfolio.setUserId(userId);
                portfolio.setStockAbr(stock.get().getStockabr());
                portfolio.setStockId(stockId);
                portfolio.setCost(randomCost);
            }
            portfolioRepository.save(portfolio);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return portfolio;
    }
}
