package com.BankingSystem.BankingApp.Repository;

import com.BankingSystem.BankingApp.Model.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    Accounts findByAccountNo(int accountNo);
}
