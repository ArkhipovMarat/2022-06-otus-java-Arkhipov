package ru.otus.tests;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

@Test
public class TestClass3 {
    @Before
    public void before3() {
    }

    @Test
    public void test3ShouldPassWithError() {
        throw new IllegalArgumentException("ТЕСТ ЗАВЕРШИЛСЯ С ОШИБКОЙ");
    }

    @Test
    public void test3ShouldPassSuccess() {
    }

    @After
    public void after3() {
    }
}
