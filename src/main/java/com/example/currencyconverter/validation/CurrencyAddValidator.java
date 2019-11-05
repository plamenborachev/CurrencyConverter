package com.example.currencyconverter.validation;

import com.example.currencyconverter.constants.GlobalConstants;
import com.example.currencyconverter.domain.models.binding.CurrencyAddBindingModel;
import com.example.currencyconverter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class CurrencyAddValidator implements Validator {
    
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyAddValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CurrencyAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CurrencyAddBindingModel currencyAddBindingModel = (CurrencyAddBindingModel) o;
        
        if (currencyAddBindingModel.getName() == null
                || currencyAddBindingModel.getName().equals("")) {
            errors.rejectValue("name",
                    GlobalConstants.CURRENCY_NAME_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE,
                    GlobalConstants.CURRENCY_NAME_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getName().length() < GlobalConstants.CURRENCY_NAME_MIN_LENGTH
                || currencyAddBindingModel.getName().length() > GlobalConstants.CURRENCY_NAME_MAX_LENGTH) {
            errors.rejectValue("name",
                    GlobalConstants.CURRENCY_NAME_LENGTH_VALIDATION_MESSAGE,
                    GlobalConstants.CURRENCY_NAME_LENGTH_VALIDATION_MESSAGE);
        }

        this.currencyRepository.findCurrenciesByName(currencyAddBindingModel.getName())
                .ifPresent((c) -> errors
                        .rejectValue("name",
                                GlobalConstants.CURRENCY_EXISTS_MESSAGE,
                                GlobalConstants.CURRENCY_EXISTS_MESSAGE));

        if (currencyAddBindingModel.getCode() == null
                || currencyAddBindingModel.getCode().equals("")) {
            errors.rejectValue("code",
                    GlobalConstants.CODE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE,
                    GlobalConstants.CODE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getCode().length() < GlobalConstants.CODE_MIN_LENGTH
                || currencyAddBindingModel.getCode().length() > GlobalConstants.CODE_MAX_LENGTH) {
            errors.rejectValue("code",
                    GlobalConstants.CODE_LENGTH_VALIDATION_MESSAGE,
                    GlobalConstants.CODE_LENGTH_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getPerUnitOfCurrency() == null
                || currencyAddBindingModel.getPerUnitOfCurrency().toString().equals("")) {
            errors.rejectValue("perUnitOfCurrency",
                    GlobalConstants.PER_UNIT_OF_CURRENCY_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE,
                    GlobalConstants.PER_UNIT_OF_CURRENCY_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getPerUnitOfCurrency() <= 0) {
            errors.rejectValue("perUnitOfCurrency",
                    GlobalConstants.PER_UNIT_OF_CURRENCY_POSITIVE_VALIDATION_MESSAGE,
                    GlobalConstants.PER_UNIT_OF_CURRENCY_POSITIVE_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getRate() == null) {
            errors.rejectValue("rate",
                    GlobalConstants.RATE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE,
                    GlobalConstants.RATE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE);
        }

        if (currencyAddBindingModel.getRate().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("rate",
                    GlobalConstants.RATE_POSITIVE_VALIDATION_MESSAGE,
                    GlobalConstants.RATE_POSITIVE_VALIDATION_MESSAGE);
        }
    }
}
