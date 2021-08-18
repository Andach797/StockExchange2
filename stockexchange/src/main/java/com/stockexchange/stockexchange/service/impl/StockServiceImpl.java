package com.stockexchange.stockexchange.service.impl;

import com.stockexchange.stockexchange.dto.StockDto;
import com.stockexchange.stockexchange.exception.ResourceNotFoundException;
import com.stockexchange.stockexchange.model.Portfolio;
import com.stockexchange.stockexchange.model.Stock;
import com.stockexchange.stockexchange.model.User;
import com.stockexchange.stockexchange.repository.StockRepository;
import com.stockexchange.stockexchange.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);


    StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public Stock createStock(String stockname, String stockabr) {
        try {
            Stock stock = new Stock();
            stock.setStockabr(stockabr);
            stock.setStockname(stockname);
            stockRepository.save(stock);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ResponseEntity<Stock> updateStock(StockDto stockDto) {
        Optional<Stock> stock = null;
        try {
            stock = stockRepository.findById(stockDto.getId());
            stock.get().setStockname(stockDto.getStockname());
            stock.get().setStockabr(stockDto.getStockabr());
            stockRepository.save(stock.get());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteStock(Long id) {
        try {
            Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock not found" + id));
            stockRepository.delete(stock);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
            return   ResponseEntity.ok(res);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    @Override
    public Optional<Stock> selectById(Long id){
        return stockRepository.findById(id);
    }
}
