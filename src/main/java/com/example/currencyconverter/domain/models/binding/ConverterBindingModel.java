package com.example.currencyconverter.domain.models.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ConverterBindingModel {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
    private BigDecimal result;

    public ConverterBindingModel() {
    }

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters!")
    public String getFromCurrency() {
        return this.fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters!")
    public String getToCurrency() {
        return this.toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    @NotNull
    @NotEmpty
    @Min(value = 1, message = "Invalid rate!")
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getResult() {
        return this.result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
