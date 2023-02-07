package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Сущность - наличные денежные средства
 */
@Data
@AllArgsConstructor
public class Cash {
    /**
     * Номинал
     */
    private Nominal nominal;

    /**
     * Количество
     */
    private long count;
}
