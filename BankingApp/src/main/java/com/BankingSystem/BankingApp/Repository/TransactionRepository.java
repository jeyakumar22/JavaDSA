package com.BankingSystem.BankingApp.Repository;

import com.BankingSystem.BankingApp.Model.Transactions;
import com.BankingSystem.BankingApp.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findByFromAccountNumberOrToAccountNumber(int fromAccountNumber, int toAccountNumber);
    Optional<Transactions> findByTransactionId(int transactionId);

}
