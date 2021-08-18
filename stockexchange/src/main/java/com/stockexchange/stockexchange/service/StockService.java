package com.stockexchange.stockexchange.service;

import com.stockexchange.stockexchange.dto.StockDto;
import com.stockexchange.stockexchange.model.Stock;
import com.stockexchange.stockexchange.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface StockService {

    Stock createStock(String stockname, String stockabr);

    Optional<Stock> selectById(Long id);

    ResponseEntity<Stock> updateStock(StockDto stockDto);

    ResponseEntity<Map<String, Boolean>> deleteStock(Long id);
}
