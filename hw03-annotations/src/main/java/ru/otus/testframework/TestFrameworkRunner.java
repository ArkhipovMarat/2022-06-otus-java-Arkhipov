package ru.otus.testframework;

import lombok.AllArgsConstructor;
import ru.otus.testframework.dto.Message;
import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ReportService;
import ru.otus.testframework.service.ValidationService;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TestFrameworkRunner {
    private final ClassFinder classFinder;
    private final ClassRunner classRunner;
    private final ReportService reportService;
    private final ValidationService validationService;

    public void runTests(String path) {
        List<Class<?>> classList = classFinder.find(path);

        List<Message> messageList = new ArrayList<>();

        for (Class<?> clazz : classList) {
            if (validationService.validate(clazz)) {
                Message message = runTestClass(clazz);
                messageList.add(message);
            }
        }

        reportService.printReport(messageList);
    }

    private Message runTestClass(Class<?> clazz) {
        try {
            return classRunner.runTestClass(clazz);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return Message.createErrorMsg(clazz.getSimpleName(),
                    MessageFormat.format("КРИТИЧЕСКАЯ ОШИБКА В РАБОТЕ ФРЕЙМВОРКА : {0}", e.getMessage()));
        }
    }
}
