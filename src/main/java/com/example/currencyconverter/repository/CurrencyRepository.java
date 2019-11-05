package com.example.currencyconverter.repository;

import com.example.currencyconverter.domain.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Optional<Currency> findCurrenciesByName(String name);

}
