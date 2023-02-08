package ru.otus.converter;

import ru.otus.model.Cash;

import java.util.List;

/**
 * Сервис конвертации денежных средств
 */
public interface CashConverter {
    /**
     * Метод конвертации заданной суммы в денежные средства
     *
     * @param sourceCashList - источник денежных средств (банкомат)
     * @param sum - запрашиваемая сумма
     */
    List<Cash> toCash(List<Cash> sourceCashList, long sum);
}
