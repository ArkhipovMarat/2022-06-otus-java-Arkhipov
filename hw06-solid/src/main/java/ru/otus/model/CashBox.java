package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.exception.NotEnoughCashException;

/**
 * Сущность - ячейка наличных денежных средств
 */
@Data
@AllArgsConstructor
public class CashBox implements Cloneable {
    /**
     * Номинал
     */
    private Nominal nominal;

    /**
     * Количество
     */
    private int count;

    /**
     * Метод добавления денежных средств
     */
    public void add(long count) {
        if (count < 0)
            throw new IllegalArgumentException(String.format("Количество добавляемых купюр не может быть отрицательным, %s",
                    count));

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
    public CashBox clone() {
        return new CashBox(nominal, count);
    }
}
