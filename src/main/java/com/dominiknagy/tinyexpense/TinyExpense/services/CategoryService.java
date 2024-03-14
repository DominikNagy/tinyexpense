package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Category retrieveCategory(long expenseCategoryId);
    List<CategoryResponse> retrieveCategories();
    Category createExpenseCategory(CreateCategoryRequest createCategoryRequest);
    void deleteCategory(long categoryId);
}
