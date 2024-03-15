package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse retrieveCategoryAsResponse(long categoryId);
    Category retrieveCategory(long categoryId);
    List<CategoryResponse> retrieveCategories();
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
    void deleteCategory(long categoryId);
}
