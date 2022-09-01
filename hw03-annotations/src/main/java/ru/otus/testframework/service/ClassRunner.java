package ru.otus.testframework.service;

/**
 * Сервис для запуска тестов в тестовых классах
 * */
public interface ClassRunner {
    void runTestClass(Class<?> clazz);
}
