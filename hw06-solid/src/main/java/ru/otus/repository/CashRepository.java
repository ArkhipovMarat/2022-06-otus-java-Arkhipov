package ru.otus.repository;

import ru.otus.model.CurrencyValue;

import java.util.Set;

public interface CashRepository {
    void put(CurrencyValue currencyValue, long count);

    long get(CurrencyValue currencyValue, long count);

    long getCount(CurrencyValue currencyValue);

    Set<CurrencyValue> getCurrencyValues();
}
