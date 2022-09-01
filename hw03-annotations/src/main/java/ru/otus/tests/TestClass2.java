package ru.otus.tests;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

@Test
public class TestClass2 {
    @Before
    public void before() {
    }

    @Test
    public void testSmthShouldPassWithError() {
        throw new IllegalArgumentException("ТЕСТ ЗАВЕРШИЛСЯ С ОШИБКОЙ");
    }

    @Test
    public void testSmthShouldPassSuccess() {
    }

    @After
    public void after() {
    }
}
