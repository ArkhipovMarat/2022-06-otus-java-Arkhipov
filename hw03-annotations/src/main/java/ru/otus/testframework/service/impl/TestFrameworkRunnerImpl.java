package ru.otus.testframework.service.impl;

import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.TestFrameworkRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestFrameworkRunnerImpl implements TestFrameworkRunner {
    private final ClassFinder classFinder = new ClassFinderImpl();
    private final ClassRunner classRunner = new ClassRunnerImpl();

    @Override
    public void runTests(String path) {
        List<Class<?>> classList = classFinder.find(path);

        List<Message> report = new ArrayList<>();

        for (Class<?> clazz : classList) {
            try {
                Message message = classRunner.runTestClass(clazz);
                report.add(message);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println("КРИТИЧЕСКАЯ ОШИБКА В РАБОТЕ ФРЕЙМВОРКА");
            }

        }


        report.forEach(System.out::println);

        int count = 0;
        int success = 0;

        for (Message message : report) {
            List<Message.Report> reportList = message.getReportList();
            for (Message.Report mreport : reportList) {
                if (mreport.getMethodType().equals("Test")) {
                    count++;
                }
                if (mreport.getStatus().equals(Message.Status.SUCCESS)
                        && mreport.getMethodType().equals("Test")) {
                    success++;
                }
            }
        }

        System.out.println("------------");
        System.out.println("ВСЕГО ПРОВЕДЕНО ТЕСТОВ: " + count);
        System.out.println("ИЗ НИХ УСПЕШНО: " + success);
    }


}
