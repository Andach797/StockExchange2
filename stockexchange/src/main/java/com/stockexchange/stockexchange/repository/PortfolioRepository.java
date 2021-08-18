package com.stockexchange.stockexchange.repository;

import com.stockexchange.stockexchange.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByUserId(Long userId);


    Portfolio findByPortfolioId(Long portfolioId);

    Portfolio findByUserIdAndStockId(Long userId, Long stockId);
    @Transactional
    Portfolio deletePortfolioByPortfolioId(long portfolioId);
}
