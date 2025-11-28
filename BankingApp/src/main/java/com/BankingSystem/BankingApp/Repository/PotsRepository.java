package com.BankingSystem.BankingApp.Repository;

import com.BankingSystem.BankingApp.Model.Accounts;
import com.BankingSystem.BankingApp.Model.Pots;
import com.BankingSystem.BankingApp.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotsRepository extends JpaRepository<Pots, Integer> {
    List<Pots> findByAccount(Accounts account);
}
