package com.example.currencyconverter.validation;

import com.example.currencyconverter.domain.entities.Currency;
import com.example.currencyconverter.domain.models.service.CurrencyServiceModel;
import org.springframework.stereotype.Component;

@Component
public class CurrencyValidationServiceImpl implements CurrencyValidationService {
    @Override
    public boolean isValid(Currency currency) {
        return currency != null;
    }

    @Override
    public boolean isValid(CurrencyServiceModel currency) {
        return currency != null;
    }
}
