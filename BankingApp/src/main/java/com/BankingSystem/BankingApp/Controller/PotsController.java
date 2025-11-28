package com.BankingSystem.BankingApp.Controller;

import com.BankingSystem.BankingApp.DTO.AddMoneyToPot;
import com.BankingSystem.BankingApp.Enums.PotStatus;
import com.BankingSystem.BankingApp.Model.Pots;
import com.BankingSystem.BankingApp.Service.AccountService;
import com.BankingSystem.BankingApp.Service.PotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pot")
public class PotsController {

    @Autowired
    private PotsService potService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create/{accountNumber}")
    public ResponseEntity<?> createPot(@PathVariable int accountNumber, @RequestBody Pots pot) {
        try {
            Pots savedPot = potService.savePot(pot, accountNumber);
            return ResponseEntity.ok(savedPot);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addMoney/{potId}")
    public ResponseEntity<String> addMoneyToPot(@PathVariable int potId, @RequestBody AddMoneyToPot req) {
        try {
            boolean success = potService.addMoneyToPot(potId, req.getAccountNumber(), req.getAmount());
            if (success) {
                return ResponseEntity.ok("Money added to pot successfully!");
            } else {
                return ResponseEntity.badRequest().body("Failed to add money to pot (check balance or pot status)");
            }
        } catch (RuntimeException e) {
            // Handles exceptions thrown from PotsService
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Fallback for unexpected exceptions
            return ResponseEntity.internalServerError().body("Something went wrong while adding money to pot");
        }
    }

    @PutMapping("/close/{potId}")
    public ResponseEntity<String> closePot(@PathVariable int potId) {
        try {
            boolean closed = potService.closePot(potId);
            if (closed) {
                return ResponseEntity.ok("Pot closed successfully!");
            } else {
                return ResponseEntity.badRequest().body("Failed to close pot!");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong while closing the pot");
        }
    }


    @GetMapping("/user/{accountNumber}")
    public ResponseEntity<?> getUserPots(@PathVariable int accountNumber) {
        try {
            List<Pots> pots = potService.getPotsByAccount(accountNumber);
            return ResponseEntity.ok(pots);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch pots for the user");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPots() {
        try {
            List<Pots> pots = potService.getAllPots();
            return ResponseEntity.ok(pots);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch all pots");
        }
    }
}
