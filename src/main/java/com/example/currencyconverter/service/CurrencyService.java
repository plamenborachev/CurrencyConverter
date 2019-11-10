package com.example.currencyconverter.service;

import com.example.currencyconverter.domain.models.service.CurrencyServiceModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {

    CurrencyServiceModel createCurrency(CurrencyServiceModel currencyServiceModel);

    List<CurrencyServiceModel> findAllCurrencies() throws IOException;

    BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount);
}
