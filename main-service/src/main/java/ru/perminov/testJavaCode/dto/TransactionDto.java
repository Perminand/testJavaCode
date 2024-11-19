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

    @NotBlank(message = "walletId не должно быть пустым")
    private String walletId;

    @NotNull(message = "operationType не должно быть null")
    private OperationType operationType;

    @NotNull(message = "amount не должно быть null")
    @Positive(message = "Должно быть больше 0")
    private Long amount;

}
