package ru.otus.exception;

public class CashBoxNotFoundException extends RuntimeException {
    public CashBoxNotFoundException(String message) {
        super(message);
    }
}
