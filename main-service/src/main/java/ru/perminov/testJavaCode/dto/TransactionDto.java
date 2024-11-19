package ru.perminov.testJavaCode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.perminov.testJavaCode.model.OperationType;

@Data
@NoArgsConstructor
public class TransactionDto {

    @NotBlank
    private String walletId;

    @NotNull
    private OperationType operationType;

    @NotNull
    @Positive
    private Long amount;

}
