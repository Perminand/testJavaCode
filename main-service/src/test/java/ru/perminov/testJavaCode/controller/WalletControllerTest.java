package ru.perminov.testJavaCode.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.model.OperationType;
import ru.perminov.testJavaCode.service.WalletService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@Import({WalletService.class})
@ExtendWith(MockitoExtension.class)
class WalletControllerTest {
    @InjectMocks
    private WalletController walletController;

    @Mock
    private WalletService walletService;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void createOk() {
        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setWalletId("33fa31a5-87d6-4906-b252-4e8e43df0064");
        transactionDto.setOperationType(OperationType.DEPOSIT);
        transactionDto.setAmount(1000L);
        assertTrue(validator.validate(transactionDto).isEmpty());
    }

    @Test
    void createFailNullFailId() {
        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setWalletId(null);
        transactionDto.setOperationType(OperationType.DEPOSIT);
        transactionDto.setAmount(1000L);
        assertFalse(validator.validate(transactionDto).isEmpty());
    }

    @Test
    void createFailNullOperationType() {
        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setWalletId("33fa31a5-87d6-4906-b252-4e8e43df0064");
        transactionDto.setOperationType(null);
        transactionDto.setAmount(1000L);
        assertFalse(validator.validate(transactionDto).isEmpty());
    }

    @Test
    void createFailNullAmount() {
        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setWalletId("33fa31a5-87d6-4906-b252-4e8e43df0064");
        transactionDto.setOperationType(OperationType.DEPOSIT);
        transactionDto.setAmount(null);
        assertFalse(validator.validate(transactionDto).isEmpty());
    }

    @Test
    void createFailNegativeAmount() {
        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setWalletId("33fa31a5-87d6-4906-b252-4e8e43df0064");
        transactionDto.setOperationType(OperationType.DEPOSIT);
        transactionDto.setAmount(-1L);
        assertFalse(validator.validate(transactionDto).isEmpty());
    }


    @Test
    void getById() {
        String uuid = "33fa31a5-87d6-4906-b252-4e8e43df0064";
        WalletDto walletDto = WalletDto.builder()
                .id(UUID.fromString("33fa31a5-87d6-4906-b252-4e8e43df0064"))
                .count(0L)
                .build();

        Mockito
                .when(walletController.getById("33fa31a5-87d6-4906-b252-4e8e43df0064"))
                .thenReturn(walletDto);

        Assertions.assertEquals(walletController.getById(uuid).getId(), UUID.fromString(uuid));
    }

    @Test
    void getByFail() {
        String uuid = "33fa31a5-87d6-4906-b252-4e8e43df0065";
        Mockito
                .when(walletController.getById(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);


        Assertions.assertThrows(EntityNotFoundException.class, () ->
                walletService.getById(uuid)
        );
    }
}