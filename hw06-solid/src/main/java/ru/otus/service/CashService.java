package ru.otus.service;

import ru.otus.model.Cash;

import java.util.List;

public interface CashService {
    void put(List<Cash> cash);

    List<Cash> get(long sum);

    List<Cash> findAll();
}
