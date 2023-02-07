package ru.otus.repository;

import ru.otus.converter.CashConverter;
import ru.otus.model.Cash;
import ru.otus.model.Nominal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CashRepositoryImpl implements CashRepository {
    private final Map<Nominal, Long> cashRepository;

    public CashRepositoryImpl(Map<Nominal, Long> cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void put(List<Cash> cashList) {
        for (Cash cash : cashList) {
            cashRepository.computeIfPresent(cash.getNominal(), (k, v) -> v + cash.getCount());
        }
    }

    @Override
    public void remove(List<Cash> cashList) {
        for (Cash cash : cashList) {
            cashRepository.computeIfPresent(cash.getNominal(), (k, v) -> v - cash.getCount());
        }
    }

    @Override
    public List<Cash> getAvailable() {
        List<Cash> cashList = new ArrayList<>();

        for (Nominal nominal : cashRepository.keySet()) {
            Long count = cashRepository.get(nominal);
            if (count != 0) {
                cashList.add(CashConverter.toCash(nominal, count));
            }
        }

        return cashList;
    }
}