package com.BankingSystem.BankingApp.Model;

import com.BankingSystem.BankingApp.Enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Entity
@Data
@Getter
@Setter
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int accountNo;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    private int pin;

    @Column(nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    private Double interestRate;
    private Double overdraftLimit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;




}







