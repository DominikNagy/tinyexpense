package com.dominiknagy.tinyexpense.TinyExpense.repositories;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.entities.ExpenseCategory;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findExpenseById(Long id);
    List<Expense> findExpensesByAccount(Account account);
    List<Expense> findExpensesByExpenseCategory(ExpenseCategory expenseCategory);
}
