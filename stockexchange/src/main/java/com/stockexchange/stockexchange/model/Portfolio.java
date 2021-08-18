package com.stockexchange.stockexchange.model;

import javax.persistence.*;

@Entity
@Table(name = "PORTFOLIO")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long portfolioId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "stockId")
    private Long stockId;

    @Column(name = "stockName")
    private String stockName;

    @Column(name = "stockAbr")
    private String stockAbr;

    @Column(name = "amount")
    private int amount;

    @Column(name = "cost")
    private int cost;

    public Portfolio() {
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockAbr() {
        return stockAbr;
    }

    public void setStockAbr(String stockAbr) {
        this.stockAbr = stockAbr;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
