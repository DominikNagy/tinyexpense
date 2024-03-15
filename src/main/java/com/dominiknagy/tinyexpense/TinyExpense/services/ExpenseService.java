package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;

import java.util.List;

public interface ExpenseService {
    ExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest);
    ExpenseResponse retrieveExpense(long expenseId);
    List<ExpenseResponse> retrieveExpenses();
    List<ExpenseResponse> retrieveExpensesInCategory(long categoryId);
    void deleteExpense(long expenseId);
}
