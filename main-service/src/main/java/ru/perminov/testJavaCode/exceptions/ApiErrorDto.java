package ru.perminov.testJavaCode.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorDto {
    private final String status;
    private final String reason;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

}

