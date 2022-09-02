package ru.otus.testframework.service;

import ru.otus.testframework.dto.Message;

import java.util.List;

public interface ReportService {
    void printReport (List<Message> report);
}
