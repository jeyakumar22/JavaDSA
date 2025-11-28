package com.BankingSystem.BankingApp.Model;

import com.BankingSystem.BankingApp.Enums.IsCredit;
import com.BankingSystem.BankingApp.Enums.Transaction;
import com.BankingSystem.BankingApp.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Entity
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(nullable = false)
    private String description;

    private int toAccountNumber;
    private int fromAccountNumber;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private Transaction transactionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsCredit isCredit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private LocalDateTime transactionDate;

}





