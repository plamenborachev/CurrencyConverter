package com.example.currencyconverter.error;

import com.example.currencyconverter.constants.GlobalConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = GlobalConstants.CURRENCY_EXISTS_MESSAGE)
public class CurrencyNameAlreadyExistsException extends RuntimeException {

    private int statusCode;

    public CurrencyNameAlreadyExistsException() {
        this.statusCode = GlobalConstants.CONFLICT_CODE;
    }

    public CurrencyNameAlreadyExistsException(String message) {
        super(message);
        this.statusCode = GlobalConstants.CONFLICT_CODE;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
