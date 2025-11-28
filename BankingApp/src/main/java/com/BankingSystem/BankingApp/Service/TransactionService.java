package com.BankingSystem.BankingApp.Service;

import com.BankingSystem.BankingApp.Enums.IsCredit;
import com.BankingSystem.BankingApp.Enums.Transaction;
import com.BankingSystem.BankingApp.Enums.TransactionStatus;
import com.BankingSystem.BankingApp.Model.Transactions;
import com.BankingSystem.BankingApp.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;

    public List<Transactions> getAllTransactions() {
        List<Transactions> list = transactionRepo.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("No transactions found");
        }
        return list;
    }

    public List<Transactions> getTransactionsByAccount(int accountNo) {
        List<Transactions> list = transactionRepo.findByFromAccountNumberOrToAccountNumber(accountNo, accountNo);
        if (list.isEmpty()) {
            throw new RuntimeException("No transactions found for account: " + accountNo);
        }
        return list;
    }

    public void saveTransaction(int toAcc, int fromAcc, double amount, boolean isCredit,
                                Transaction type, String desc, TransactionStatus status) {

        Transactions tx = new Transactions();
        tx.setToAccountNumber(toAcc);
        tx.setFromAccountNumber(fromAcc);
        tx.setAmount(amount);
        tx.setTransactionType(type);
        tx.setIsCredit(isCredit ? IsCredit.CREDIT : IsCredit.DEBIT);
        tx.setTransactionStatus(status);
        tx.setDescription(desc);

        transactionRepo.save(tx);
    }

}
