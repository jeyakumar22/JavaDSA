package com.BankingSystem.BankingApp.Service;

import com.BankingSystem.BankingApp.DTO.DepositRequest;
import com.BankingSystem.BankingApp.DTO.DetailsRequest;
import com.BankingSystem.BankingApp.DTO.TransferRequest;
import com.BankingSystem.BankingApp.DTO.WithdrawRequest;
import com.BankingSystem.BankingApp.Enums.AccountType;
import com.BankingSystem.BankingApp.Enums.Transaction;
import com.BankingSystem.BankingApp.Enums.TransactionStatus;
import com.BankingSystem.BankingApp.Model.Accounts;
import com.BankingSystem.BankingApp.Repository.AccountRepository;
import com.BankingSystem.BankingApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepo;
    public Accounts createAccount(Accounts account) {
        try {
            if (account.getAccountType() == AccountType.SAVINGS) {
                if (account.getInterestRate() == null || account.getInterestRate() <= 0)
                    throw new RuntimeException("Interest rate required for Savings account");
                account.setOverdraftLimit(null);
            } else if (account.getAccountType() == AccountType.CURRENT) {
                if (account.getOverdraftLimit() == null || account.getOverdraftLimit() <= 0)
                    throw new RuntimeException("Overdraft limit required for Current account");
                account.setInterestRate(null);
            }

            Accounts newAccount = accountRepo.save(account);

            double initialAmount = newAccount.getBalance();
            if (initialAmount > 0) {
                Accounts adminAccount = accountRepo.findById(1)
                        .orElseThrow(() -> new RuntimeException("Admin account not found"));

                adminAccount.setBalance(adminAccount.getBalance() - initialAmount);
                newAccount.setBalance(initialAmount);

                accountRepo.save(adminAccount);
                accountRepo.save(newAccount);

                transactionService.saveTransaction(
                        adminAccount.getAccountNo(),
                        newAccount.getAccountNo(),
                        initialAmount,
                        false,
                        Transaction.DEPOSIT,
                        "Initial balance debited from admin account for " + newAccount.getAccountNo(),
                        TransactionStatus.SUCCESS
                );
                transactionService.saveTransaction(
                        newAccount.getAccountNo(),
                        adminAccount.getAccountNo(),
                        initialAmount,
                        true,
                        Transaction.DEPOSIT,
                        "Initial balance credited to new account " + newAccount.getAccountNo(),
                        TransactionStatus.SUCCESS
                );

            }

            return newAccount;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create account: " + e.getMessage());
        }
    }

    public Accounts deposit(DepositRequest request) {
        try {
            Accounts account = accountRepo.findByAccountNo(request.getAccountNo());
            if (account == null)
                throw new RuntimeException("Account not found");

            if (account.getPin() != request.getPin())
                throw new RuntimeException("Invalid PIN");

            if (request.getAmount() <= 0)
                throw new RuntimeException("Invalid deposit amount");

            Accounts adminAccount = accountRepo.findByAccountNo(100);
            if (adminAccount == null)
                throw new RuntimeException("Admin account not found");

            account.setBalance(account.getBalance() + request.getAmount()); // Credit to user
            adminAccount.setBalance(adminAccount.getBalance() - request.getAmount()); // Debit from admin

            accountRepo.save(adminAccount);
            Accounts savedAccount = accountRepo.save(account);
            transactionService.saveTransaction(
                    100,
                    account.getAccountNo(),
                    request.getAmount(),
                    false,
                    Transaction.DEPOSIT,
                    "Deposit debited from admin account for " + account.getAccountNo(),
                    TransactionStatus.SUCCESS
            );
            transactionService.saveTransaction(
                    account.getAccountNo(),
                    100,
                    request.getAmount(),
                    true,
                    Transaction.DEPOSIT,
                    "Deposit credited to account " + account.getAccountNo(),
                    TransactionStatus.SUCCESS
            );


            return savedAccount;

        } catch (Exception e) {
            throw new RuntimeException("Deposit failed: " + e.getMessage());
        }
    }

    public Accounts withdraw(WithdrawRequest request) {
        try {
            Accounts account = accountRepo.findByAccountNo(request.getAccountNo());
            if (account == null)
                throw new RuntimeException("Account not found");

            if (account.getPin() != request.getPin())
                throw new RuntimeException("Invalid PIN");

            double withdrawAmount = request.getAmount();
            if (withdrawAmount <= 0)
                throw new RuntimeException("Invalid withdraw amount");

            if (account.getAccountType() == AccountType.SAVINGS) {
                if (account.getBalance() < withdrawAmount)
                    throw new RuntimeException("Insufficient balance");
            } else if (account.getAccountType() == AccountType.CURRENT) {
                if (account.getBalance() + account.getOverdraftLimit() < withdrawAmount)
                    throw new RuntimeException("Exceeds overdraft limit");
            }

            account.setBalance(account.getBalance() - withdrawAmount);
            transactionService.saveTransaction(
                    account.getAccountNo(),
                    account.getAccountNo(),
                    request.getAmount(),
                    false,
                    Transaction.WITHDRAW,
                    "Withdrawal from account " + account.getAccountNo(),
                    TransactionStatus.SUCCESS
            );
            return accountRepo.save(account);
        } catch (Exception e) {
            throw new RuntimeException("Withdrawal failed: " + e.getMessage());
        }
    }

    public String transfer(TransferRequest request) {
        try {
            Accounts fromAcc = accountRepo.findByAccountNo(request.getFromAccountNo());
            Accounts toAcc = accountRepo.findByAccountNo(request.getToAccountNo());

            if (fromAcc == null || toAcc == null)
                throw new RuntimeException("One or both accounts not found");

            if (fromAcc.getPin() != request.getPin())
                throw new RuntimeException("Invalid PIN");

            double amount = request.getAmount();
            if (amount <= 0)
                throw new RuntimeException("Invalid transfer amount");

            if (fromAcc.getAccountType() == AccountType.SAVINGS && fromAcc.getBalance() < amount)
                throw new RuntimeException("Insufficient balance");
            if (fromAcc.getAccountType() == AccountType.CURRENT && fromAcc.getBalance() + fromAcc.getOverdraftLimit() < amount)
                throw new RuntimeException("Exceeds overdraft limit");

            fromAcc.setBalance(fromAcc.getBalance() - amount);
            toAcc.setBalance(toAcc.getBalance() + amount);

            accountRepo.save(fromAcc);
            accountRepo.save(toAcc);

            transactionService.saveTransaction(
                    fromAcc.getAccountNo(),
                    toAcc.getAccountNo(),
                    amount,
                    false,
                    Transaction.TRANSFER,
                    "Transferred ₹" + amount + " to account " + toAcc.getAccountNo(),
                    TransactionStatus.SUCCESS
            );

            transactionService.saveTransaction(
                    toAcc.getAccountNo(),
                    fromAcc.getAccountNo(),
                    amount,
                    true,
                    Transaction.TRANSFER,
                    "Received ₹" + amount + " from account " + fromAcc.getAccountNo(),
                    TransactionStatus.SUCCESS
            );

            return "Transfer successful from " + fromAcc.getAccountNo() + " to " + toAcc.getAccountNo();
        } catch (Exception e) {
            throw new RuntimeException("Transfer failed: " + e.getMessage());
        }
    }


    public Accounts addInterest(int accountNo) {
        try {
            Accounts account = accountRepo.findByAccountNo(accountNo);
            if (account == null)
                throw new RuntimeException("Account not found");

            if (account.getAccountType() != AccountType.SAVINGS)
                throw new RuntimeException("Interest can be added only for Savings accounts");

            double interest = account.getBalance() * (account.getInterestRate() / 100);

            Accounts adminAccount = accountRepo.findById(1)
                    .orElseThrow(() -> new RuntimeException("Admin account not found"));

            account.setBalance(account.getBalance() + interest);
            adminAccount.setBalance(adminAccount.getBalance() - interest);

            accountRepo.save(adminAccount);
            accountRepo.save(account);

            transactionService.saveTransaction(
                    adminAccount.getAccountNo(),
                    account.getAccountNo(),
                    interest,
                    false,
                    Transaction.INTEREST,
                    "Interest debited from admin account for account " + account.getAccountNo(),
                    TransactionStatus.SUCCESS
            );
            transactionService.saveTransaction(
                    account.getAccountNo(),
                    adminAccount.getAccountNo(),
                    interest,
                    true,
                    Transaction.INTEREST,
                    "Interest credited to account " + account.getAccountNo(),
                    TransactionStatus.SUCCESS
            );


            return account;

        } catch (Exception e) {
            throw new RuntimeException("Failed to add interest: " + e.getMessage());
        }
    }



    public Accounts getAccountDetails(DetailsRequest request) {
        try {
            Accounts account = accountRepo.findByAccountNo(request.getAccountNo());
            if (account == null)
                throw new RuntimeException("Account not found");

            if (account.getPin() != request.getPin())
                throw new RuntimeException("Invalid PIN");

            return account;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch account details: " + e.getMessage());
        }
    }


}
