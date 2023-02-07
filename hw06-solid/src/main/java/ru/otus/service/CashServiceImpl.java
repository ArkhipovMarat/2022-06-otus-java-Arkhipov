package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.converter.CashConverter;
import ru.otus.model.Cash;
import ru.otus.repository.CashRepository;

import java.util.List;

@RequiredArgsConstructor
public class CashServiceImpl implements CashService {
    private final CashRepository cashRepository;

    @Override
    public void put(List<Cash> cashList) {
        cashRepository.put(cashList);
    }

    @Override
    public List<Cash> get(long sum) {
        List<Cash> cashList = toCash(sum);

        cashRepository.remove(cashList);

        return cashList;
    }

    @Override
    public List<Cash> getAvailable() {
        return cashRepository.getAvailable();
    }

    private List<Cash> toCash(long sum) {
        List<Cash> availableCashList = getAvailable();

        return CashConverter.toCash(availableCashList, sum);
    }
}
