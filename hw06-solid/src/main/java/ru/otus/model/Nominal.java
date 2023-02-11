package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Значения номиналов валюты
 */
@AllArgsConstructor
@Getter
public enum Nominal {
    R100(100),
    R200(200),
    R500(500),
    R1000(1000),
    R5000(5000);

    private int value;
}
