package ru.perminov.testJavaCode.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.mapper.TransactionMapper;
import ru.perminov.testJavaCode.mapper.WalletMapper;
import ru.perminov.testJavaCode.model.OperationType;
import ru.perminov.testJavaCode.model.Transaction;
import ru.perminov.testJavaCode.model.Wallet;
import ru.perminov.testJavaCode.repository.TransactionRepository;
import ru.perminov.testJavaCode.repository.WalletRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public WalletDto create(TransactionDto dto) {
        Wallet wallet = getWallet(dto.getWalletId());
        Transaction tr = TransactionMapper.toEntity(dto);
        tr.setWallet(wallet);
        tr.setCreated(LocalDateTime.now());
        transactionRepository.save(tr);

        if (dto.getOperationType() == OperationType.DEPOSIT) {
            wallet.setCount(wallet.getCount() + dto.getAmount());
        } else if (dto.getOperationType() == OperationType.WITHDRAW) {
            wallet.setCount(wallet.getCount() - dto.getAmount());
        }
        return WalletMapper.toDto(wallet);
    }

    @Override
    public WalletDto getById(String uuid) {
        return WalletMapper.toDto(getWallet(uuid));
    }

    private Wallet getWallet(String uuid) {
        return walletRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new EntityNotFoundException("Кошелек не найден"));
    }
}
