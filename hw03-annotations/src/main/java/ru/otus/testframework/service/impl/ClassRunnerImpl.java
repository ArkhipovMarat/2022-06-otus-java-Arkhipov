package ru.otus.testframework.service.impl;

import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ValidationService;

public class ClassRunnerImpl implements ClassRunner {
    private final ValidationService validationService = new ValidationServiceImpl();

    @Override
    public void runTestClass(Class<?> clazz) {
    }
}
