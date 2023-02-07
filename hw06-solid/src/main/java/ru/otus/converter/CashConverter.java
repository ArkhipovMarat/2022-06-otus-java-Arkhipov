package ru.otus.converter;

import lombok.experimental.UtilityClass;
import ru.otus.model.Cash;
import ru.otus.model.Nominal;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

@UtilityClass
public class CashConverter {
    public Cash toCash(Nominal nominal, long count) {
        return new Cash(nominal, count);
    }

    public List<Cash> toCash(List<Cash> sourceCashList, long sum) {
        List<Cash> resultCashList = new ArrayList<>();

        sourceCashList.sort(comparing(Cash::getNominal).reversed());

        for (Cash cash : sourceCashList) {
            int nominalValue = cash.getNominal().getValue();

            if (sum >= nominalValue) {
                long count = getCount(sum, nominalValue, cash.getCount());
                sum -= nominalValue * count;
                resultCashList.add(toCash(cash.getNominal(), count));
            }
        }

        return resultCashList;
    }

    private long getCount(long sum, long nominalValue, long sourceCashCount) {
        long count = sum / nominalValue;
        return sourceCashCount - count >= 0 ? count : sourceCashCount;
    }
}
