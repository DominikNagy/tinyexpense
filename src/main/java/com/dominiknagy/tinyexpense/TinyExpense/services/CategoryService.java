package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CategoryService {
    CategoryResponse retrieveCategoryAsResponse(long categoryId) throws ChangeSetPersister.NotFoundException;
    Category retrieveCategory(long categoryId);
    List<CategoryResponse> retrieveCategories();
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    void deleteCategory(long categoryId);
    void createDefaultCategory(User user);
    CategoryResponse updateCategory(long categoryId, CategoryRequest categoryRequest);
}
