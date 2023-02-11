package ru.otus.service;

import ru.otus.model.CashBox;

import java.util.List;

/**
 * Сервис операций с наличными средствами
 */
public interface CashService {
    /**
     * Положить наличные
     */
    void put(List<CashBox> cashBoxes);

    /**
     * Получить наличные
     */
    List<CashBox> get(int sum);

    /**
     * Узнать о количестве доступных наличных
     */
    List<CashBox> getAvailable();
}
