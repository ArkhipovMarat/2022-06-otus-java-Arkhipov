package ru.otus.converter;

import ru.otus.exception.CashConverterException;
import ru.otus.model.Cash;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;


public class GreedyCashConverter implements CashConverter {
    public List<Cash> toCash(List<Cash> sourceCashList, long sum) {
        List<Cash> resultCashList = new ArrayList<>();

        sourceCashList.sort(comparing(Cash::getNominal).reversed());

        for (Cash cash : sourceCashList) {
            int nominalValue = cash.getNominal().getValue();

            if (sum >= nominalValue) {
                long count = getCount(sum, nominalValue, cash.getCount());
                sum -= nominalValue * count;
                resultCashList.add(new Cash(cash.getNominal(), count));
            }
        }

        if (sum > 0) throw new CashConverterException("В источнике недостаточно денежных средств");

        return resultCashList;
    }

    private long getCount(long sum, long nominalValue, long sourceCashCount) {
        long count = sum / nominalValue;
        return sourceCashCount - count >= 0 ? count : sourceCashCount;
    }
}
