package ru.otus;

import ru.otus.model.Cash;

import java.util.List;

public interface AtmService {
    void put(List<Cash> cash);

    List<Cash> get(long sum);

    List<Cash> findAll();
}
