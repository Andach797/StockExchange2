package com.stockexchange.stockexchange.dto;

public class StockDto {

    private Long id;
    private String stockname;
    private String stockabr;

    public String getStockname() {
        return stockname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getStockabr() {
        return stockabr;
    }

    public void setStockabr(String stockabr) {
        this.stockabr = stockabr;
    }
}
