package ru.otus.converter;

import ru.otus.model.CashBox;

import java.util.List;

/**
 * Сервис конвертации денежных средств
 */
public interface CashBoxConverter {
    /**
     * Метод конвертации заданной суммы в денежные средства
     *
     * @param sourceCashBoxList - источник денежных средств (банкомат)
     * @param sum               - запрашиваемая сумма
     */
    List<CashBox> toCash(List<CashBox> sourceCashBoxList, int sum);
}
