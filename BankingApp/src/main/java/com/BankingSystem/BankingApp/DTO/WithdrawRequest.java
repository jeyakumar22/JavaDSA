package com.BankingSystem.BankingApp.DTO;

import lombok.Data;

@Data
public class WithdrawRequest {
    private int accountNo;
    private int pin;
    private double amount;
}
