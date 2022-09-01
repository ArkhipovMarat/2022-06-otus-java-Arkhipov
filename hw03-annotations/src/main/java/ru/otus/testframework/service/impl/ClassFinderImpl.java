package ru.otus.testframework.service.impl;

import ru.otus.testframework.service.ClassFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassFinderImpl implements ClassFinder {
    @Override
    public List<Class<?>> find(String path) {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {

            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, pathToPackage(path)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.printf("Ошибка чтения тестовых классов в пакете %s, причина: %s%n", path, e.getMessage());
        }
        return null;
    }


    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.out.printf("Не найден класс с именем %s в пакете %s, причина: %s%n", className, packageName, e.getMessage());
        }
        return null;
    }

    private String pathToPackage(String path) {
        return path.replaceAll("\\/", ".");
    }
}
