package ru.otus.repository;

import ru.otus.exception.CashNotFoundException;
import ru.otus.model.Cash;

import java.util.ArrayList;
import java.util.List;

public class CashRepositoryImpl implements CashRepository {
    private final List<Cash> cashRepository;

    public CashRepositoryImpl(List<Cash> cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void put(List<Cash> cashList) {
        cashList.forEach(cash -> get(cash).add(cash.getCount()));
    }

    @Override
    public void remove(List<Cash> cashList) {
        cashList.forEach(cash -> get(cash).remove(cash.getCount()));
    }

    @Override
    public List<Cash> getAvailable() {
        List<Cash> cashList = new ArrayList<>();

        cashRepository.forEach(cash -> cashList.add(cash.clone()));

        return cashList;
    }

    private Cash get(Cash cash) {
        return cashRepository.stream().filter(v -> v.equals(cash)).findFirst()
                .orElseThrow(() -> new CashNotFoundException(String.format("Не найдена ячейка с номиналом - %s",
                        cash.getNominal())));
    }
}