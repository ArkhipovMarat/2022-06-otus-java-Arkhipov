package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Сущность - Денежная единица
 */
@Data
@AllArgsConstructor
public class Cash {
    /**
     * Номинал
     */
    private CurrencyValue currencyValue;

    /**
     * Количество
     */
    private long count;
}
