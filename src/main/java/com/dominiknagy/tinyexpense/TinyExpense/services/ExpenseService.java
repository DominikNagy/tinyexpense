package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(CreateExpenseRequest createExpenseRequest);
    Expense retrieveExpense(long expenseId);
    List<ExpenseResponse> retrieveExpenses();
    List<Expense> retrieveExpensesInCategory(long categoryId);
    void deleteExpense(long expenseId);
}
