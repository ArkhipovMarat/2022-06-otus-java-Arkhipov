package ru.otus.exception;

public class CashNotFoundException extends RuntimeException {
    public CashNotFoundException(String message) {
        super(message);
    }
}
