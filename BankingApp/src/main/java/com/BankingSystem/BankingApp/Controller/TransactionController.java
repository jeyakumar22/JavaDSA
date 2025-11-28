package com.BankingSystem.BankingApp.Controller;


import com.BankingSystem.BankingApp.Model.Transactions;
import com.BankingSystem.BankingApp.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions() {
        try {
            List<Transactions> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching transactions: " + e.getMessage());
        }
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<?> getTransactionsByAccount(@PathVariable int accountNo) {
        try {
            List<Transactions> transactions = transactionService.getTransactionsByAccount(accountNo);
            if (transactions.isEmpty()) {
                return ResponseEntity.ok("No transactions found for account: " + accountNo);
            }
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching transactions for account " + accountNo + ": " + e.getMessage());
        }
    }

}

