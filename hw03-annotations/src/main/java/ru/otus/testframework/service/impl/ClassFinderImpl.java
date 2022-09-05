package ru.otus.testframework.service.impl;

import ru.otus.testframework.service.ClassFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassFinderImpl implements ClassFinder {
    private final ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    @Override
    public List<Class<?>> find(String path) {
        return findClass(path, new ArrayList<>());
    }

    private List<Class<?>> findClass(String path, List<Class<?>> classList) {
        try (InputStream stream = classLoader.getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {

            for (String file : reader.lines().collect(Collectors.toList())) {
                if (!file.contains(".")) {
                    findClass(path + "/" + file, classList);
                }
                if (file.endsWith(".class")) {
                    classList.add(getClass(file, pathToPackage(path)));
                }
            }
        } catch (IOException e) {
            System.out.printf("Ошибка чтения тестовых классов в пакете %s, причина: %s%n", path, e.getMessage());
        }
        return classList;
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
        return path.replaceAll("/", ".");
    }
}
