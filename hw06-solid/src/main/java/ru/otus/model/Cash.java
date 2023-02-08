package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.exception.NotEnoughCashException;

/**
 * Сущность - ячейка наличных денежных средств
 */
@Data
@AllArgsConstructor
public class Cash implements Cloneable{
    /**
     * Номинал
     */
    private Nominal nominal;

    /**
     * Количество
     */
    private long count;

    /**
     * Метод добавления денежных средств
     */
    public void add(long count) {
        this.count += count;
    }

    /**
     * Метод изъятия денежных средств
     */
    public void remove(long count) {
        if (count > this.count)
            throw new NotEnoughCashException(String.format("Не достаточно денежных средств номиналом - %s",
                    nominal));

        this.count -= count;
    }

    @Override
    public Cash clone() {
        return new Cash(nominal, count);
    }
}
