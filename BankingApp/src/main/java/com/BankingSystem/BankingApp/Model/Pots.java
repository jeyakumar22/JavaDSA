package com.BankingSystem.BankingApp.Model;


import com.BankingSystem.BankingApp.Enums.PotStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity
@Data
public class Pots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PotStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Accounts account;
}


