package com.stockexchange.stockexchange.service;

import com.stockexchange.stockexchange.model.Portfolio;
import com.stockexchange.stockexchange.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioService {
    List<Portfolio> getAllPortfolio(Long userId);

    Portfolio sellStock(Long portfolioId, int amount);

    Portfolio buyStock(Long stockId, Long userId, int amount);
}
