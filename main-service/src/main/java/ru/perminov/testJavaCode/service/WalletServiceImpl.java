package ru.perminov.testJavaCode.service;

import lombok.RequiredArgsConstructor;
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
    @Transactional
    public WalletDto create(TransactionDto dto) {
        Wallet wallet = walletRepository.findByIdForUpdate(UUID.fromString(dto.getWalletId()))
                .orElseThrow(() -> new EntityNotFoundException("Кошелек не найден"));
        transactionService.create(dto, wallet);
        switch (dto.getOperationType()) {
            case DEPOSIT:
                depositToWallet(wallet, dto.getAmount());
                break;
            case WITHDRAW:
                withdrawToWallet(wallet, dto.getAmount());
                break;
            default:
                throw new UnsupportedOperationException("Неизвестная операция");
        }
        walletRepository.save(wallet);
        return WalletMapper.toDto(wallet);
    }

    @Override
    public WalletDto getById(String uuid) {
        Wallet wallet = walletRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new EntityNotFoundException("Кошелек не найден"));
        return WalletMapper.toDto(wallet);
    }


    private void depositToWallet(Wallet wallet, Long amount) {
        wallet.setCount(wallet.getCount() + amount);
    }

    private void withdrawToWallet(Wallet wallet, Long amount) {
        if (wallet.getCount() - amount < 0) {
            throw new ValidationException("Не достаточно средств");
        } else {
            wallet.setCount(wallet.getCount() - amount);
        }
    }

}
