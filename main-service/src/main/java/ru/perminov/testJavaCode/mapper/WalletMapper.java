package ru.perminov.testJavaCode.mapper;

import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.model.Wallet;

public class WalletMapper {
//    public static Wallet toEntity(WalletDto dto) {
//        Wallet wallet = new Wallet();
//        wallet.setOperationType(dto.getOperationType());
//        wallet.setAmount(dto.getAmount());
//        return wallet;
//
//    }

    public static WalletDto toDto(Wallet wallet) {
        return WalletDto.builder()
                .id(wallet.getUuid())
                .count(wallet.getCount())
                .build();
    }
}
