package com.example.currencyconverter.validation;

import com.example.currencyconverter.domain.entities.Currency;
import com.example.currencyconverter.domain.models.service.CurrencyServiceModel;

public interface CurrencyValidationService {

    boolean isValid(Currency currency);

    boolean isValid(CurrencyServiceModel currency);
}
