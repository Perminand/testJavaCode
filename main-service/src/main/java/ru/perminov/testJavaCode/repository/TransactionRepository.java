package ru.perminov.testJavaCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.testJavaCode.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
