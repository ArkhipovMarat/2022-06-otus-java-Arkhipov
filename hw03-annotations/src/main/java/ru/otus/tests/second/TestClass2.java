package ru.otus.tests.second;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

@Test
public class TestClass2 {
    @Before
    public void before2() {
    }

    @Test
    public void test2ShouldPassWithError() {
        throw new IllegalArgumentException("ТЕСТ ЗАВЕРШИЛСЯ С ОШИБКОЙ");
    }

    @Test
    public void test2ShouldPassSuccess() {
    }

    @After
    public void after2() {
    }
}
