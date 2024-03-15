package com.dominiknagy.tinyexpense.TinyExpense.repositories;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findExpenseByIdAndUser(Long id, User user);
    List<Expense> findExpensesByUser(User user);
    List<Expense> findExpensesByCategoryAndUser(Category category, User user);
}
