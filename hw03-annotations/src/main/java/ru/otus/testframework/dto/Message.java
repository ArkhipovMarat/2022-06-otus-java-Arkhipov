package ru.otus.testframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.text.MessageFormat;
import java.util.List;

/**
 * Отчет о прохождении тестов для класса
 */
@AllArgsConstructor
@Data
@Builder
public class Message {
    private String className;
    private List<Report> reportList;

    @AllArgsConstructor
    public enum Status {
        ERROR, SUCCESS
    }

    @AllArgsConstructor
    @Data
    public static class Report {
        private String methodName;
        private String methodType;
        private Status status;
        private String message;

        @Override
        public String toString() {
            return MessageFormat.format("\nназвание метода: {0}\nстатус: {1}\nсообщение: {2}\n",
                    methodName, status, message);
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("\nОтчет о прохождении тестов для класса {0}\n{1}",
                className, reportList);
    }
}
