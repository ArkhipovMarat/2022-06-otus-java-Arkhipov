package ru.otus.converter;

import lombok.experimental.UtilityClass;
import ru.otus.model.Cash;
import ru.otus.model.CurrencyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@UtilityClass
public class CashConverter {
    public Cash toCash(CurrencyValue currencyValue, long count) {
        return new Cash(currencyValue, count);
    }

    public List<Cash> toCash(List<Cash> sourceCash, long sum) {
        List<Cash> cash = new ArrayList<>();

        Map<CurrencyValue, Long> sourceCashByCurrencyValue = sourceCash.stream()
                .collect(Collectors.toMap(Cash::getCurrencyValue, Cash::getCount));

        for (Map.Entry<CurrencyValue, Long> entry : sourceCashByCurrencyValue.entrySet()) {
            long sourceCashValue = entry.getKey().getValue();
            long sourceCashCount = entry.getValue();

            if (sum >= sourceCashValue) {
                long count = getCount(sum, sourceCashValue, sourceCashCount);
                sum -= sourceCashValue * count;
                cash.add(new Cash(entry.getKey(), count));
            }
        }

        return cash;
    }

    private long getCount(long sum, long sourceCashValue, long sourceCashCount) {
        long count = sum / sourceCashValue;
        return sourceCashCount - count >= 0 ? count : sourceCashCount;
    }
}
