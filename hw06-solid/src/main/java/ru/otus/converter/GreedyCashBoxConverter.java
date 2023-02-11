package ru.otus.converter;

import ru.otus.exception.CashBoxConverterException;
import ru.otus.model.CashBox;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;


public class GreedyCashBoxConverter implements CashBoxConverter {
    public List<CashBox> toCash(List<CashBox> sourceCashBoxList, int sum) {
        List<CashBox> resultCashBoxList = new ArrayList<>();

        sourceCashBoxList.sort(comparing(CashBox::getNominal).reversed());

        for (CashBox cashBox : sourceCashBoxList) {
            int nominalValue = cashBox.getNominal().getValue();

            if (sum >= nominalValue) {
                int count = getCount(sum, nominalValue, cashBox.getCount());
                sum -= nominalValue * count;
                resultCashBoxList.add(new CashBox(cashBox.getNominal(), count));
            }
        }

        if (sum > 0) throw new CashBoxConverterException("В источнике недостаточно денежных средств");

        return resultCashBoxList;
    }

    private int getCount(int sum, int nominalValue, int sourceCashCount) {
        int count = sum / nominalValue;
        return sourceCashCount - count >= 0 ? count : sourceCashCount;
    }
}
