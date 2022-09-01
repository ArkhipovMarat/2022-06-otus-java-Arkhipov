package ru.otus.testframework.service.impl;

import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.service.ValidationService;

import java.lang.reflect.Method;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validate(Class<?> clazz) throws IllegalArgumentException {
        if (!clazz.isAnnotationPresent(Test.class)) {
            throw new IllegalArgumentException("Класс не является тестовым");
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                return;
            }
        }
        throw new IllegalArgumentException("В классе не обнаружены методы для тестов");
    }
}
