package com.example.currencyconverter.domain.models.view;

import java.math.BigDecimal;

public class CurrencyAllViewModel {

    private String id;
    private String name;
    private String code;
    private Integer perUnitOfCurrency;
    private BigDecimal rate;
    private BigDecimal bnbRate;

    public CurrencyAllViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPerUnitOfCurrency() {
        return this.perUnitOfCurrency;
    }

    public void setPerUnitOfCurrency(Integer perUnitOfCurrency) {
        this.perUnitOfCurrency = perUnitOfCurrency;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getBnbRate() {
        return this.bnbRate;
    }

    public void setBnbRate(BigDecimal bnbRate) {
        this.bnbRate = bnbRate;
    }
}
