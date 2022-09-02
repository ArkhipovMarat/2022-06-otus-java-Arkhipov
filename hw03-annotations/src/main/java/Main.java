import ru.otus.testframework.TestFrameworkRunner;

public class Main {
    public static void main(String[] args) {
        // пакет с тестовыми файлами
        final String path = "ru/otus/tests";

        // раннер для запуска тестов
        TestFrameworkRunner testRunner = new TestFrameworkRunner();
        testRunner.runTests(path);
    }
}
