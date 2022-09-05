package ru.otus.testframework.service;

import java.util.List;

/**
* Сервис для поиска классов в заданном пакете
* */
public interface ClassFinder {
    List<Class<?>> find(String path);
}
