package ru.perminov.testJavaCode.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perminov.testJavaCode.dto.TransactionDto;
import ru.perminov.testJavaCode.dto.WalletDto;
import ru.perminov.testJavaCode.service.WalletService;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/wallet")
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDto create(@RequestBody TransactionDto dto) {
        log.info("Пришел POST запрос на добавление записи {}", dto);
        return walletService.create(dto);
    }

    @GetMapping("/wallets/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public WalletDto getById(@PathVariable String uuid) {
        log.info("Пришел GET запрос на получение всех записей");
        return walletService.getById(uuid);
    }
}
