package ru.otus.testframework.service;

/**
 * Раннер для запуска тестов
 * */
public interface TestFrameworkRunner {
    /**
     * метод запускающий тесты
     * @param path - пакет в котором находятся файлы с тестами
    * */
    void runTests(String path);
}