package ru.otus.testframework.service.impl;

import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.TestFrameworkRunner;

import java.util.List;

public class TestFrameworkRunnerImpl implements TestFrameworkRunner {
    private final ClassFinder classFinder = new ClassFinderImpl();
    private final ClassRunner classRunner = new ClassRunnerImpl();

    @Override
    public void runTests(String path) {
        List<Class<?>> classList = classFinder.find(path);

        classList.forEach(classRunner::runTestClass);
    }
}
