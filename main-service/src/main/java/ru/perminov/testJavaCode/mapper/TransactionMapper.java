package ru.perminov.testJavaCode.mapper;

import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.model.Transaction;

public class TransactionMapper {
    public static Transaction toEntity(TransactionDto dto) {
        Transaction tr = new Transaction();
        tr.setOperationType(dto.getOperationType());
        tr.setAmount(dto.getAmount());
        return tr;
    }
}
