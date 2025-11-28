package com.BankingSystem.BankingApp.DTO;

import lombok.Data;

@Data
public class AddMoneyToPot {
    private int accountNumber;
    private double amount;
    private String description;
}
