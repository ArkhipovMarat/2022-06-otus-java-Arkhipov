import ru.otus.testframework.TestFrameworkRunner;
import ru.otus.testframework.service.ClassFinder;
import ru.otus.testframework.service.ClassRunner;
import ru.otus.testframework.service.ReportService;
import ru.otus.testframework.service.ValidationService;
import ru.otus.testframework.service.impl.ClassFinderImpl;
import ru.otus.testframework.service.impl.ClassRunnerImpl;
import ru.otus.testframework.service.impl.ReportServiceImpl;
import ru.otus.testframework.service.impl.ValidationServiceImpl;

public class Main {
    public static void main(String[] args) {
        // пакет с тестовыми файлами
        final String path = "ru/otus/tests";

        // инициализация тестового фреймворка
        ClassFinder classFinder = new ClassFinderImpl();
        ClassRunner classRunner = new ClassRunnerImpl();
        ReportService reportService = new ReportServiceImpl();
        ValidationService validationService = new ValidationServiceImpl();

        // раннер для запуска тестов
        TestFrameworkRunner testRunner = new TestFrameworkRunner(classFinder, classRunner, reportService, validationService);
        testRunner.runTests(path);
    }
}
