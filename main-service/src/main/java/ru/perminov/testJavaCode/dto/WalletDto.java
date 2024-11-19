package ru.perminov.testJavaCode.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@NotNull
public class WalletDto {


    private UUID id;

    private Long count;
}
