package ru.otus.service;

import ru.otus.model.Cash;

import java.util.List;

/**
 * Сервис обработки операция с наличными средствами
 */
public interface CashService {
    /**
     * Положить наличные
     */
    void put(List<Cash> cash);

    /**
     * Получить наличные
     */
    List<Cash> get(long sum);

    /**
     * Узнать о количестве доступных наличных
     */
    List<Cash> getAvailable();
}
