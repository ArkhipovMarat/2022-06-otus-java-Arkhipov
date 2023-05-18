package ru.otus.dataprocessor;

import java.util.Map;

public interface Serializer {
    /**
     * формирует результирующий json и сохраняет его в файл
     */
    void serialize(Map<String, Double> data);
}
