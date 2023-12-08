package com.dominiknagy.tinyexpense.TinyExpense.repositories;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordRepository extends JpaRepository<Password, UUID> {
    Optional<Password> findPasswordByAccount(Account account);
}
