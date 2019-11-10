package com.example.currencyconverter.domain.models.view;

import java.math.BigDecimal;

public class CurrencyViewModel {

    private String id;
    private String name;
    private BigDecimal perUnitOfCurrency;
    private BigDecimal rate;

    public CurrencyViewModel() {
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

    public BigDecimal getPerUnitOfCurrency() {
        return this.perUnitOfCurrency;
    }

    public void setPerUnitOfCurrency(BigDecimal perUnitOfCurrency) {
        this.perUnitOfCurrency = perUnitOfCurrency;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
