package ru.perminov.testJavaCode.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@NotNull
public class WalletDto {


    private UUID id;

    private Long count;
}
