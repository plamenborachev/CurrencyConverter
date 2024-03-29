package com.example.currencyconverter.service;

import com.example.currencyconverter.constants.GlobalConstants;
import com.example.currencyconverter.domain.entities.Currency;
import com.example.currencyconverter.domain.models.service.CurrencyServiceModel;
import com.example.currencyconverter.error.CurrencyNameAlreadyExistsException;
import com.example.currencyconverter.repository.CurrencyRepository;
import com.example.currencyconverter.validation.CurrencyValidationService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyValidationService currencyValidation;
    private final ModelMapper modelMapper;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyValidationService currencyValidation, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyValidation = currencyValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrencyServiceModel createCurrency(CurrencyServiceModel currencyServiceModel) {
        if (!currencyValidation.isValid(currencyServiceModel)){
            throw new IllegalArgumentException();
        }

        Currency currency = this.currencyRepository
                .findCurrenciesByName(currencyServiceModel.getName())
                .orElse(null);

        if (currency != null){
            throw new CurrencyNameAlreadyExistsException(GlobalConstants.CURRENCY_EXISTS_MESSAGE);
        }

        currency = this.modelMapper.map(currencyServiceModel, Currency.class);
        currency = this.currencyRepository.save(currency);

        return this.modelMapper.map(currency, CurrencyServiceModel.class);
    }

    @Override
    public List<CurrencyServiceModel> findAllCurrencies() throws IOException {
        List<CurrencyServiceModel> allCurrencies = this.currencyRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CurrencyServiceModel.class))
                .collect(Collectors.toList());

        String url = "http://bnb.bg/Statistics/StExternalSector/StExchangeRates/StERForeignCurrencies/index.htm?toLang=_EN";
        Document document = Jsoup.connect(url).get();
        Elements rates = document.select("td.center");

        for (CurrencyServiceModel currency : allCurrencies) {
            for (int i = 0; i < rates.size(); i += 3) {
                String val = rates.get(i).text();
                if (val.equals(currency.getCode())){
                    BigDecimal bnbRate = BigDecimal.valueOf(Double.parseDouble(rates.get(i + 1).text()));
                    currency.setBnbRate(bnbRate);
                    break;
                }
            }
        }
        return allCurrencies;
    }

    @Override
    public BigDecimal convertCurrency(String fromCurrencyStr, String toCurrencyStr, BigDecimal amount) {
        Currency fromCurrency = this.currencyRepository
                .findCurrenciesByName(fromCurrencyStr)
                .orElse(null);

        if (fromCurrency == null){
            throw new CurrencyNameAlreadyExistsException(fromCurrencyStr + GlobalConstants.CURRENCY_DOES_NOT_EXISTS_MESSAGE);
        }

        Currency toCurrency = this.currencyRepository
                .findCurrenciesByName(toCurrencyStr)
                .orElse(null);

        if (toCurrency == null){
            throw new CurrencyNameAlreadyExistsException(toCurrencyStr + GlobalConstants.CURRENCY_DOES_NOT_EXISTS_MESSAGE);
        }

        BigDecimal result = amount
                .divide(fromCurrency.getPerUnitOfCurrency(), 6, RoundingMode.HALF_UP)
                .multiply(fromCurrency.getRate())
                .divide(toCurrency.getRate(), 6, RoundingMode.HALF_UP)
                .multiply(toCurrency.getPerUnitOfCurrency());

        return result.setScale(6, RoundingMode.HALF_UP);
    }
}
