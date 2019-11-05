package com.example.currencyconverter.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
public class Currency {

    private String id;
    private String name;
    private String code;
    private BigDecimal perUnitOfCurrency;
    private BigDecimal rate;

    public Currency() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(
            name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "currency_name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "per_unit_of_currency")
    public BigDecimal getPerUnitOfCurrency() {
        return this.perUnitOfCurrency;
    }

    public void setPerUnitOfCurrency(BigDecimal perUnitOfCurrency) {
        this.perUnitOfCurrency = perUnitOfCurrency;
    }

    @Column(name = "rate", nullable = false, precision = 19, scale = 5, columnDefinition="DECIMAL(19,5)")
    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}


