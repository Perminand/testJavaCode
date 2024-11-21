package ru.perminov.testJavaCode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.exceptions.errors.EntityNotFoundException;
import ru.perminov.testJavaCode.exceptions.errors.ValidationException;
import ru.perminov.testJavaCode.mapper.WalletMapper;
import ru.perminov.testJavaCode.model.Wallet;
import ru.perminov.testJavaCode.repository.WalletRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final TransactionService transactionService;


    @Override
    @Retryable(maxAttempts = 5)
    @Transactional
    public WalletDto create(TransactionDto dto) {
        Wallet wallet = getWallet(dto.getWalletId());
        transactionService.create(dto, wallet);
        switch (dto.getOperationType()) {
            case DEPOSIT:
                wallet.setCount(wallet.getCount() + dto.getAmount());
                break;
            case WITHDRAW:
                wallet.setCount(wallet.getCount() - dto.getAmount());
                break;
            default:
                throw new UnsupportedOperationException("Неизвестная операция");
        }
        walletRepository.save(wallet); // Автоматическая проверка версии
        return WalletMapper.toDto(wallet);
    }

    @Override
    public WalletDto getById(String uuid) {
        return WalletMapper.toDto(getWallet(uuid));
    }


    private Wallet depositToWallet(Wallet wallet, Long amount) {
        wallet.setCount(wallet.getCount() + amount);
        return wallet;
    }

    private Wallet withdrawToWallet(Wallet wallet, Long amount) {
        if (wallet.getCount() - amount < 0) {
            throw new ValidationException("Не достаточно средств");
        } else {
            wallet.setCount(wallet.getCount() - amount);
        }
        return wallet;
    }

    private Wallet getWallet(String uuid) {
        return walletRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new EntityNotFoundException("Кошелек не найден"));
    }
}
