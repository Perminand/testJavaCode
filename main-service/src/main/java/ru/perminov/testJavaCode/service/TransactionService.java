package ru.perminov.testJavaCode.service;

import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.model.Wallet;

public interface TransactionService {
    void create(TransactionDto dto, Wallet wallet);
}
