package com.BankingSystem.BankingApp.DTO;

import lombok.Data;


@Data
public class DepositRequest {
    private int accountNo;
    private int pin;
    private double amount;


}
