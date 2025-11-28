package com.BankingSystem.BankingApp.Service;

import com.BankingSystem.BankingApp.Enums.PotStatus;
import com.BankingSystem.BankingApp.Enums.Transaction;
import com.BankingSystem.BankingApp.Enums.TransactionStatus;
import com.BankingSystem.BankingApp.Model.Accounts;
import com.BankingSystem.BankingApp.Model.Pots;
import com.BankingSystem.BankingApp.Repository.AccountRepository;
import com.BankingSystem.BankingApp.Repository.PotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotsService {

    @Autowired
    private PotsRepository potRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionService transactionService;

    public Pots savePot(Pots pot, int accountNumber) {
        Accounts account = accountRepo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        pot.setAccount(account);
        pot.setStatus(PotStatus.ACTIVE);

        double initialAmount = pot.getBalance();
        if (initialAmount > 0) {
            if (account.getBalance() < initialAmount)
                throw new RuntimeException("Insufficient balance in main account");

            account.setBalance(account.getBalance() - initialAmount);
            pot.setBalance(initialAmount);

            accountRepo.save(account);
            potRepo.save(pot);


            transactionService.saveTransaction(
                    account.getAccountNo(),
                    pot.getId(),
                    initialAmount,
                    false, // debit
                    Transaction.DEPOSIT,
                    "Initial pot amount transferred to pot: " + pot.getTitle(),
                    TransactionStatus.SUCCESS
            );

            transactionService.saveTransaction(
                    pot.getId(),
                    account.getAccountNo(),
                    initialAmount,
                    true, // credit
                    Transaction.DEPOSIT,
                    "Initial amount added to new pot: " + pot.getTitle(),
                    TransactionStatus.SUCCESS
            );

        } else {
            pot.setBalance(0.0);
            potRepo.save(pot);
        }

        return pot;
    }


    public boolean addMoneyToPot(int potId, int fromAccountNumber, double amount) {
        try {
            Pots pot = potRepo.findById(potId)
                    .orElseThrow(() -> new RuntimeException("Pot not found"));
            Accounts fromAcc = accountRepo.findByAccountNo(fromAccountNumber);
            if (fromAcc == null) throw new RuntimeException("Account not found");

            if (pot.getStatus() != PotStatus.ACTIVE)
                throw new RuntimeException("Pot is not active");

            if (amount <= 0) throw new RuntimeException("Invalid amount");

            if (fromAcc.getBalance() < amount)
                throw new RuntimeException("Insufficient balance in main account");

            fromAcc.setBalance(fromAcc.getBalance() - amount);
            pot.setBalance(pot.getBalance() + amount);

            accountRepo.save(fromAcc);
            potRepo.save(pot);

            transactionService.saveTransaction(
                    pot.getId(),
                    fromAcc.getAccountNo(),
                    amount,
                    false, // debit from main account
                    Transaction.TRANSFER,
                    "Money transferred from main account to pot: " + pot.getTitle(),
                    TransactionStatus.SUCCESS
            );

            transactionService.saveTransaction(
                    fromAcc.getAccountNo(),
                    pot.getId(),
                    amount,
                    true, // credit to pot
                    Transaction.DEPOSIT,
                    "Money added to pot: " + pot.getTitle(),
                    TransactionStatus.SUCCESS
            );

            return true;
        } catch (Exception e) {
            transactionService.saveTransaction(
                    potId,
                    fromAccountNumber,
                    amount,
                    true,
                    Transaction.DEPOSIT,
                    "Pot deposit failed: " + e.getMessage(),
                    TransactionStatus.FAILED
            );
            return false;
        }
    }

    public boolean closePot(int potId) {
        try {
            Pots pot = potRepo.findById(potId)
                    .orElseThrow(() -> new RuntimeException("Pot not found"));

            if (pot.getStatus() == PotStatus.CANCELED)
                throw new RuntimeException("Pot already closed");

            Accounts mainAccount = pot.getAccount();
            if (mainAccount == null)
                throw new RuntimeException("Main account not linked to this pot");

            double potBalance = pot.getBalance();

            if (potBalance > 0) {
                mainAccount.setBalance(mainAccount.getBalance() + potBalance);
                pot.setBalance(0.0);

                accountRepo.save(mainAccount);
                potRepo.save(pot);

                transactionService.saveTransaction(
                        mainAccount.getAccountNo(),
                        pot.getId(),
                        potBalance,
                        false,
                        Transaction.WITHDRAW,
                        "Pot closed: money withdrawn from pot " + pot.getTitle(),
                        TransactionStatus.SUCCESS
                );

                transactionService.saveTransaction(
                        pot.getId(),
                        mainAccount.getAccountNo(),
                        potBalance,
                        true,
                        Transaction.DEPOSIT,
                        "Pot closed: money credited back to main account",
                        TransactionStatus.SUCCESS
                );
            }

            pot.setStatus(PotStatus.CANCELED);
            potRepo.save(pot);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Pots> getPotsByAccount(int accountNumber) {
        Accounts acc = accountRepo.findByAccountNo(accountNumber);
        if (acc == null) throw new RuntimeException("Account not found");
        return potRepo.findByAccount(acc);
    }

    public List<Pots> getAllPots() {
        List<Pots> pots = potRepo.findAll();

        if (pots.isEmpty()) {
            throw new RuntimeException("No pots found in the system");
        }
        return pots;
    }
}
