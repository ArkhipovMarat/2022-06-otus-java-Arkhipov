package ru.otus.testframework.service.impl;

import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.service.ValidationService;

import java.lang.reflect.Method;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Test.class)) {
            return false;
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                return true;
            }
        }
        return false;
    }
}
