package ru.perminov.testJavaCode.exceptions.errors;

public class ValidationException extends RuntimeException {
    public ValidationException(String s) {
        super(s);
    }
}
