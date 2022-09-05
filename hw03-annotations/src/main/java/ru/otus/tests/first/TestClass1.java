package ru.otus.tests.first;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

@Test
public class TestClass1 {
    @Before
    public void before1() {
    }

    @Test
    public void test1ShouldPassWithError() {
        throw new IllegalArgumentException("ТЕСТ ЗАВЕРШИЛСЯ С ОШИБКОЙ");
    }

    @Test
    public void test1ShouldPassSuccess() {
    }

    @After
    public void after1() {
    }
}
