package ru.otus.repository;

import ru.otus.exception.CashBoxNotFoundException;
import ru.otus.model.CashBox;

import java.util.ArrayList;
import java.util.List;

public class CashBoxRepositoryImpl implements CashBoxRepository {
    private final List<CashBox> cashBoxRepository;

    public CashBoxRepositoryImpl(List<CashBox> cashBoxList) {
        cashBoxRepository = cashBoxList;
    }

    @Override
    public void put(List<CashBox> cashBoxList) {
        cashBoxList.forEach(cashBox -> get(cashBox).add(cashBox.getCount()));
    }

    @Override
    public void remove(List<CashBox> cashBoxList) {
        cashBoxList.forEach(cashBox -> get(cashBox).remove(cashBox.getCount()));
    }

    @Override
    public List<CashBox> getAvailable() {
        List<CashBox> cashBoxList = new ArrayList<>();

        cashBoxRepository.forEach(cash -> cashBoxList.add(cash.clone()));

        return cashBoxList;
    }

    private CashBox get(CashBox cashBox) {
        return cashBoxRepository.stream().filter(v -> v.equals(cashBox)).findFirst()
                .orElseThrow(() -> new CashBoxNotFoundException(String.format("Не найдена ячейка с номиналом - %s",
                        cashBox.getNominal())));
    }
}