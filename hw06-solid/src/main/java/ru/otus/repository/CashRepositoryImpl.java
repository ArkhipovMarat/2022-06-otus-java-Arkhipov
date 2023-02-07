package ru.otus.repository;

import ru.otus.model.CurrencyValue;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class CashRepositoryImpl implements CashRepository {
    private final Map<CurrencyValue, Long> cashRepository;

    public CashRepositoryImpl() {
        cashRepository = new EnumMap<>(CurrencyValue.class);
        cashRepository.put(CurrencyValue.R100, 10L);
        cashRepository.put(CurrencyValue.R200, 10L);
        cashRepository.put(CurrencyValue.R500, 10L);
        cashRepository.put(CurrencyValue.R1000, 10L);
        cashRepository.put(CurrencyValue.R5000, 10L);
    }


    @Override
    public void put(CurrencyValue currencyValue, long count) {
        cashRepository.computeIfPresent(currencyValue, (k, v) -> v + count);
    }

    @Override
    public long get(CurrencyValue currencyValue, long count) {
        return cashRepository.computeIfPresent(currencyValue, (k, v) -> v - count);
    }

    @Override
    public long getCount(CurrencyValue currencyValue) {
        return cashRepository.get(currencyValue);
    }

    @Override
    public Set<CurrencyValue> getCurrencyValues() {
        return cashRepository.keySet();
    }
}
