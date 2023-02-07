package ru.otus.service;

import lombok.AllArgsConstructor;
import ru.otus.converter.CashConverter;
import ru.otus.model.Cash;
import ru.otus.model.CurrencyValue;
import ru.otus.repository.CashRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CashServiceImpl implements CashService {
    private final CashRepository cashRepository;

    @Override
    public void put(List<Cash> cashList) {
        cashList.forEach(cash -> cashRepository.put(cash.getCurrencyValue(), cash.getCount()));
    }

    @Override
    public List<Cash> get(long sum) {
        return getSumm(sum);
    }

    @Override
    public List<Cash> findAll() {
        return cashRepository.getCurrencyValues().stream()
                .map(currencyValue -> CashConverter.toCash(currencyValue,
                        cashRepository.getCount(currencyValue)))
                .collect(Collectors.toList());
    }

    private List<Cash> getSumm(long sum) {
        List<Cash> cash = CashConverter.toCash(findAll(), sum);

        cash.forEach(cash1 -> cashRepository.get(cash1.getCurrencyValue(), cash1.getCount()));

        return cash;
    }
}
