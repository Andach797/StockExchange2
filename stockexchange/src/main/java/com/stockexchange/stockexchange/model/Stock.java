package com.stockexchange.stockexchange.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stocksdb")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25,unique=true)
    private String stockname;

    @Column(length = 25,unique=true)
    private String stockabr;

    public Stock() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockname() {
        return stockname;
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

    public Stock(Long id, String stockname, String stockabr) {
        this.id = id;
        this.stockname = stockname;
        this.stockabr = stockabr;
    }
}


