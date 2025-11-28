package com.BankingSystem.BankingApp.Controller;

import com.BankingSystem.BankingApp.DTO.DepositRequest;
import com.BankingSystem.BankingApp.DTO.DetailsRequest;
import com.BankingSystem.BankingApp.DTO.TransferRequest;
import com.BankingSystem.BankingApp.DTO.WithdrawRequest;
import com.BankingSystem.BankingApp.Model.Accounts;
import com.BankingSystem.BankingApp.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Accounts account) {
        try {
            Accounts createdAccount = accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating account: " + e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request) {
        try {
            Accounts updatedAccount = accountService.deposit(request);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error during deposit: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request) {
        try {
            Accounts updatedAccount = accountService.withdraw(request);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error during withdrawal: " + e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        try {
            String message = accountService.transfer(request);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error during transfer: " + e.getMessage());
        }
    }


    @PostMapping("/addInterest")
    public ResponseEntity<?> addInterest(@RequestBody Map<String, Integer> request) {
        try {
            int accountNo = request.get("accountNo");
            Accounts updatedAccount = accountService.addInterest(accountNo);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding interest: " + e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseEntity<?> getAccountDetails(@RequestBody DetailsRequest request) {
        try {
            Accounts account = accountService.getAccountDetails(request);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching account details: " + e.getMessage());
        }
    }
}

