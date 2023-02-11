package ru.otus.repository;

import ru.otus.model.CashBox;

import java.util.List;

public interface CashBoxRepository {
    void put(List<CashBox> cashBoxList);

    void remove(List<CashBox> cashBoxList);

    List<CashBox> getAvailable();
}
