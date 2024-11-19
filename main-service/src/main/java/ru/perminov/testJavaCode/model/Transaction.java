package ru.perminov.testJavaCode.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType operationType;

    @Column(nullable = false)
    @Positive
    private Long amount;

}
