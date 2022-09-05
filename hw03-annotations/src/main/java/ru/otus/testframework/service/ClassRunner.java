package ru.otus.testframework.service;

import ru.otus.testframework.dto.Message;

import java.lang.reflect.InvocationTargetException;

/**
 * Сервис для запуска тестов в тестовых классах
 * */
public interface ClassRunner {
    Message runTestClass(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
