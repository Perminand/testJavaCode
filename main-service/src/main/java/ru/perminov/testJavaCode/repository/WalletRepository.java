package ru.perminov.testJavaCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.testJavaCode.model.Wallet;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
