package com.dominiknagy.tinyexpense.TinyExpense.repositories;

import com.dominiknagy.tinyexpense.TinyExpense.entities.ExpenseCategory;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findExpenseCategoryById(Long id);
    List<ExpenseCategory> findExpenseCategoriesByUser(User user);
}
