package ru.otus.testframework.service.impl;

import lombok.AllArgsConstructor;
import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ValidationService;

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

@AllArgsConstructor
public class ClassRunnerImpl implements ClassRunner {
    private final ValidationService validationService;

    @Override
    public Message runTestClass(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            validationService.validate(clazz);
        } catch (IllegalArgumentException ignored) {
            System.out.println();
        }

        String className = clazz.getSimpleName();
        Object object = clazz.getDeclaredConstructor().newInstance();
        List<Report> reportList = new ArrayList<>();

        processMethod(object, clazz, Before.class, reportList);
        processMethod(object, clazz, Test.class, reportList);
        processMethod(object, clazz, After.class, reportList);

        return Message
                .builder()
                .className(className)
                .reportList(reportList)
                .build();
    }

    private void processMethod(Object object, Class<?> clazz, Class<? extends Annotation> annotation, List<Report> reportList) throws IllegalAccessException {
        List<Method> methodsByAnnotation = getMethodsByAnnotation(clazz, annotation);

        for (Method method : methodsByAnnotation) {
            try {
                method.invoke(object);
                reportList.add(new Report(method.getName(), annotation.getSimpleName(), SUCCESS, null));
            } catch (InvocationTargetException e) {
                reportList.add(new Report(method.getName(), annotation.getSimpleName(), ERROR, e.getTargetException().getMessage()));
            }
        }
    }

    private List<Method> getMethodsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }
}
