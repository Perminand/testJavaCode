package ru.perminov.testJavaCode.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.mapper.WalletMapper;
import ru.perminov.testJavaCode.model.OperationType;
import ru.perminov.testJavaCode.model.Transaction;
import ru.perminov.testJavaCode.model.Wallet;
import ru.perminov.testJavaCode.repository.TransactionRepository;
import ru.perminov.testJavaCode.repository.WalletRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @Mock
    WalletRepository walletRepository;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    private WalletServiceImpl walletService;
    private Wallet wallet = null;
    private TransactionDto transactionDto = null;
    private Transaction transaction = null;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
        wallet.setUuid(UUID.fromString("33fa31a5-87d6-4906-b252-4e8e43df0064"));
        wallet.setCount(0L);


        transactionDto = new TransactionDto();
        transactionDto.setWalletId("33fa31a5-87d6-4906-b252-4e8e43df0064");
        transactionDto.setOperationType(OperationType.DEPOSIT);
        transactionDto.setAmount(1000L);

        transaction = new Transaction();
        transaction.setCreated(LocalDateTime.now());
        transaction.setWallet(wallet);
        transaction.setOperationType(transactionDto.getOperationType());
        transaction.setAmount(transactionDto.getAmount());
    }

    @Test
    void createOk() {
        Mockito
                .when(walletRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(wallet));
        Mockito
                .when(transactionRepository.save(Mockito.any(Transaction.class)))
                .thenReturn(transaction);
        WalletDto walletDto = walletService.create(transactionDto);
        wallet.setCount(1000L);
        Assertions.assertEquals(walletDto, WalletMapper.toDto(wallet));

    }

    @Test
    void createFailNoUUID() {
        wallet.setUuid(UUID.fromString("b316c28f-fd46-44ac-83c2-7332fce86997"));
        Mockito
                .when(walletRepository.findById(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> walletService.create(transactionDto)
        );

    }


    @Test
    void getByIdOk() {
        Mockito
                .when(walletRepository.findById(UUID.fromString(transactionDto.getWalletId())))
                .thenReturn(Optional.ofNullable(wallet));
        Assertions.assertEquals(WalletMapper.toDto(wallet), walletService.getById(transactionDto.getWalletId()));
    }

    @Test
    void getByIdFail() {
        Mockito
                .when(walletRepository.findById(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class, () ->
                walletService.getById("b316c28f-fd46-44ac-83c2-7332fce86997")
        );
    }
}