import ru.otus.testframework.service.TestFrameworkRunner;
import ru.otus.testframework.service.impl.TestFrameworkRunnerImpl;

public class Main {
    public static void main(String[] args) {
        // пакет с тестовыми файлами
        final String path = "ru/otus/tests";

        // раннер для запуска тестов
        TestFrameworkRunner testRunner = new TestFrameworkRunnerImpl();
        testRunner.runTests(path);
    }
}
