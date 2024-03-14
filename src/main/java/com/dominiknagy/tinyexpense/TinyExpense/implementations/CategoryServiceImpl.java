package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.CategoryRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.CategoryService;
import com.dominiknagy.tinyexpense.TinyExpense.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Override
    public Category retrieveCategory(long expenseCategoryId) {
        return categoryRepository.findExpenseCategoryById(expenseCategoryId).orElse(null);
    }

    @Override
    public List<CategoryResponse> retrieveCategories(String userEmail) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category :
                categoryRepository.findCategoriesByUser(userService.retrieveUserByEmail(userEmail))) {

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setName(category.getCategoryName());
            categoryResponse.setColor(category.getColor());

            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    @Override
    public Category createCategory(
            CreateCategoryRequest createCategoryRequest, String userEmail) {

        Category category = new Category();
        category.setCategoryName(createCategoryRequest.getCategoryName());
        category.setUser(userService.retrieveUserByEmail(userEmail));
        category.setColor(createCategoryRequest.getColor());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
