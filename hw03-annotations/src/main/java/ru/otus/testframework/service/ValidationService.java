package ru.otus.testframework.service;

public interface ValidationService {
    void validate(Class<?> clazz) throws IllegalArgumentException;
}
