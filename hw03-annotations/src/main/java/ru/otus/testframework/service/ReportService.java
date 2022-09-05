package ru.otus.testframework.service;

import ru.otus.testframework.dto.Message;

import java.util.List;

/**
 * Сервис для подготовки и печати отчета о прохождении тестов
* */
public interface ReportService {
    void printReport (List<Message> report);
}
