package ru.perminov.testJavaCode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.mapper.TransactionMapper;
import ru.perminov.testJavaCode.model.Transaction;
import ru.perminov.testJavaCode.model.Wallet;
import ru.perminov.testJavaCode.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;


    @Override
    public void create(TransactionDto dto, Wallet wallet) {
        Transaction transaction = TransactionMapper.toEntity(dto);
        transaction.setWallet(wallet);
        transaction.setCreated(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
}
