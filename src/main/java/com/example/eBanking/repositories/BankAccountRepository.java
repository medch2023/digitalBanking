package com.example.eBanking.repositories;

import com.example.eBanking.entities.BankAccount;
import com.example.eBanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
