package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;
import java.util.Map;

public interface Processor {
    /**
     * группирует выходящий список по name, при этом суммирует поля value
     */
    Map<String, Double> process(List<Measurement> data);
}
