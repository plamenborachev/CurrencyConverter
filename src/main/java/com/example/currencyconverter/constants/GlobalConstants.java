package com.example.currencyconverter.constants;

public class GlobalConstants {

    public static final String CURRENCY_EXISTS_MESSAGE = "Currency already exists!";

    public static final String CURRENCY_NAME_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE
            = "Currency name cannot be null or empty!";
    public static final int CURRENCY_NAME_MIN_LENGTH = 3;
    public static final int CURRENCY_NAME_MAX_LENGTH = 20;
    public static final String CURRENCY_NAME_LENGTH_VALIDATION_MESSAGE
            = "Currency name must be between 3 and 20 characters!";

    public static final String CODE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE = "Currency code cannot be null or empty!";
    public static final int CODE_MIN_LENGTH = 3;
    public static final int CODE_MAX_LENGTH = 3;
    public static final String CODE_LENGTH_VALIDATION_MESSAGE = "Currency code must be 3 characters!";

    public static final String PER_UNIT_OF_CURRENCY_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE
            = "Per Unit Of Currency value cannot be null or empty!";
    public static final String PER_UNIT_OF_CURRENCY_POSITIVE_VALIDATION_MESSAGE
            = "Per Unit Of Currency value must be positive!";

    public static final String RATE_NOT_NULL_OR_EMPTY_VALIDATION_MESSAGE = "Currency rate cannot be null or empty!";
    public static final String RATE_POSITIVE_VALIDATION_MESSAGE = "Currency rate must be positive!";

    public static final String CURRENCY_DOES_NOT_EXISTS_MESSAGE = " currency does not exist!";

    public static int CONFLICT_CODE = 409;



}
