package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.converter.CashBoxConverter;
import ru.otus.model.CashBox;
import ru.otus.repository.CashBoxRepository;

import java.util.List;

@RequiredArgsConstructor
public class CashServiceImpl implements CashService {
    private final CashBoxRepository cashBoxRepository;
    private final CashBoxConverter cashBoxConverter;

    @Override
    public void put(List<CashBox> cashBoxList) {
        cashBoxRepository.put(cashBoxList);
    }

    @Override
    public List<CashBox> get(int sum) {
        List<CashBox> cashBoxList = toCash(sum);

        cashBoxRepository.remove(cashBoxList);

        return cashBoxList;
    }

    @Override
    public List<CashBox> getAvailable() {
        return cashBoxRepository.getAvailable();
    }

    private List<CashBox> toCash(int sum) {
        List<CashBox> availableCashBoxList = getAvailable();

        return cashBoxConverter.toCash(availableCashBoxList, sum);
    }
}
