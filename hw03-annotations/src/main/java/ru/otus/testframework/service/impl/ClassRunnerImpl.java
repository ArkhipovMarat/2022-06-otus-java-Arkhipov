package ru.otus.testframework.service.impl;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.testframework.dto.Message.Report;
import static ru.otus.testframework.dto.Message.Status.ERROR;
import static ru.otus.testframework.dto.Message.Status.SUCCESS;

public class ClassRunnerImpl implements ClassRunner {
    @Override
    public Message runTestClass(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String className = clazz.getSimpleName();
        List<Report> reportList = new ArrayList<>();

        List<Method> beforeMethods = getMethodsByAnnotation(clazz, Before.class);
        List<Method> afterMethods = getMethodsByAnnotation(clazz, After.class);

        for (Method testMethod : getMethodsByAnnotation(clazz, Test.class)) {
            Object object = clazz.getDeclaredConstructor().newInstance();

            for (Method beforeMethod : beforeMethods) {
                processMethod(object, beforeMethod, Before.class, reportList);
            }

            processMethod(object, testMethod, Test.class, reportList);

            for (Method afterMethod : afterMethods) {
                processMethod(object, afterMethod, After.class, reportList);
            }
        }

        return Message
                .builder()
                .className(className)
                .reportList(reportList)
                .build();
    }

    private void processMethod(Object object, Method method, Class<? extends Annotation> annotation, List<Report> reportList) throws IllegalAccessException {
        try {
            method.invoke(object);
            reportList.add(new Report(method.getName(), annotation.getSimpleName(), SUCCESS, null));
        } catch (InvocationTargetException e) {
            reportList.add(new Report(method.getName(), annotation.getSimpleName(), ERROR, e.getTargetException().getMessage()));
        }

    }

    private List<Method> getMethodsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }
}
