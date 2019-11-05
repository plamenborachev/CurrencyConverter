package com.example.currencyconverter.domain.models.service;

import java.math.BigDecimal;

public class CurrencyServiceModel {

    private String id;
    private String name;
    private String code;
    private Integer perUnitOfCurrency;
    private BigDecimal rate;

    public CurrencyServiceModel() {
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
}
