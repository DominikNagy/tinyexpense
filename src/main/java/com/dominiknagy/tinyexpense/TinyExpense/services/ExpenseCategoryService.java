package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.ExpenseCategory;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;

import java.util.List;

public interface ExpenseCategoryService {
    ExpenseCategory retrieveExpenseCategory(long expenseCategoryId);
    List<CategoryResponse> retrieveExpenseCategories(String accountId);
    ExpenseCategory createExpenseCategory(CreateExpenseCategoryRequest createExpenseCategoryRequest, String accountId);
    void deleteCategory(long categoryId);
}
