package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;

public interface Loader {
    /**
     * читает файл, парсит и возвращает результат
     */
    List<Measurement> load();
}
