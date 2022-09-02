package ru.otus.testframework;

import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ReportService;
import ru.otus.testframework.service.impl.ClassFinderImpl;
import ru.otus.testframework.service.impl.ClassRunnerImpl;
import ru.otus.testframework.service.impl.ReportServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestFrameworkRunner {
    private final ClassFinder classFinder = new ClassFinderImpl();
    private final ClassRunner classRunner = new ClassRunnerImpl();
    private final ReportService reportService = new ReportServiceImpl();

    public void runTests(String path) {
        List<Class<?>> classList = classFinder.find(path);

        List<Message> messageList = new ArrayList<>();

        for (Class<?> clazz : classList) {
            try {
                Message message = classRunner.runTestClass(clazz);
                messageList.add(message);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println("КРИТИЧЕСКАЯ ОШИБКА В РАБОТЕ ФРЕЙМВОРКА");
            }
        }

        reportService.printReport(messageList);
    }
}
