package ru.perminov.testJavaCode.exceptions.errors;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String massage) {
        super(massage);
    }
}