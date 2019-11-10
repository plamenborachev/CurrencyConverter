package com.example.currencyconverter.domain.models.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CurrencyAddBindingModel {

    private String name;
    private String code;
    private Integer perUnitOfCurrency;
    private BigDecimal rate;

    public CurrencyAddBindingModel() {
    }

    @Size(min = 3, max = 20, message = "Currency name must be between 3 and 20 characters!")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3, max = 3, message = "Currency code must be 3 characters!")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Min(value = 1, message = "Invalid value!")
    public Integer getPerUnitOfCurrency() {
        return this.perUnitOfCurrency;
    }

    public void setPerUnitOfCurrency(Integer perUnitOfCurrency) {
        this.perUnitOfCurrency = perUnitOfCurrency;
    }

    @Min(value = 0, message = "Invalid rate!")
    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
