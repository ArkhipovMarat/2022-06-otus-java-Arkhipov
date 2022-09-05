package ru.otus.testframework.service;

/**
* Сервис валидации тестовых классов
* */
public interface ValidationService {
    /**
    * метод проверяет является ли класс тестовым - содержит аннотации @Test
     * @return true - класс является тестовым
    * */
    boolean validate(Class<?> clazz);
}
