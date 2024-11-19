package ru.perminov.testJavaCode.exceptions.errors;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String massage) {
        super(massage);
    }
}
