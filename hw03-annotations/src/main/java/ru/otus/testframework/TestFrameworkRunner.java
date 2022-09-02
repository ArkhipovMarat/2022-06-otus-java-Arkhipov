package ru.otus.testframework;

import lombok.AllArgsConstructor;
import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ReportService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TestFrameworkRunner {
    private final ClassFinder classFinder;
    private final ClassRunner classRunner;
    private final ReportService reportService;

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
