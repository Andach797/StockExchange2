package com.stockexchange.stockexchange.repository;


import com.stockexchange.stockexchange.model.ERole;
import com.stockexchange.stockexchange.model.Role;
import com.stockexchange.stockexchange.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByStockname(String stock);
}
