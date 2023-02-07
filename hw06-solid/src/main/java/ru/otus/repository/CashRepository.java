package ru.otus.repository;

import ru.otus.model.Cash;

import java.util.List;

public interface CashRepository {
    void put(List<Cash> cashList);

    void remove(List<Cash> cashList);

    List<Cash> getAvailable();
}
