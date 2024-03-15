package com.dominiknagy.tinyexpense.TinyExpense.repositories;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID uuid);
    Optional<User> findUserByEmail(String userEmail);
}
