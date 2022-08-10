package ru.otus;

import com.google.common.base.Joiner;

import java.util.Map;

/**
 * Guava example - Joiner, joins pieces of text, joins map entries etc.
 */
public class HelloOtus {
    public static void showGuavaExample() {
        System.out.println("Guava example: Map Joiner");

        var map = Map.of("first", "Hello", "second", "Otus");

        System.out.printf("Source map: %s%n", map);

        String mapAsString = Joiner.on(", ").withKeyValueSeparator(" = ").join(map);

        System.out.printf("Result string: %s%n", mapAsString);
    }
}