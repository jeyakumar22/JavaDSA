package com.BankingSystem.BankingApp.DTO;

import lombok.Data;

@Data
public class TransferRequest {
    private int fromAccountNo;
    private int toAccountNo;
    private int pin;
    private double amount;
}