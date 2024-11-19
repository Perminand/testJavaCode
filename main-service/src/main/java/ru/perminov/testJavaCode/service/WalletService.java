package ru.perminov.testJavaCode.service;

import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;

public interface WalletService {
    WalletDto create(TransactionDto dto);

    WalletDto getById(String uuid);
}
