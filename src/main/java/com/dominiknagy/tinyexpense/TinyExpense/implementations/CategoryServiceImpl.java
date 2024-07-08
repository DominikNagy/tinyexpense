package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import com.dominiknagy.tinyexpense.TinyExpense.exception.ResourceNotFoundException;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.CategoryRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.CategoryService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.Mapper;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category retrieveCategory(long categoryId) {
        return categoryRepository.findCategoryByIdAndUser(categoryId, UserUtils.authedUser()).orElseThrow();
    }

    @Override
    public CategoryResponse retrieveCategoryAsResponse(long categoryId) {
        Category category = categoryRepository.findCategoryByIdAndUser(categoryId, UserUtils.authedUser())
                .orElseThrow(() -> new ResourceNotFoundException("Category could not be found"));
        return Mapper.mapCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> retrieveCategories() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category : categoryRepository.findCategoriesByUser(UserUtils.authedUser())) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setName(category.getCategoryName());
            categoryResponse.setColor(category.getColor());

            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setUser(UserUtils.authedUser());
        category.setColor(categoryRequest.getColor());

        return Mapper.mapCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category category = categoryRepository.findCategoryByIdAndUser(categoryId, UserUtils.authedUser()).orElseThrow();
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void createDefaultCategory(User user) {
        Category category = new Category();
        category.setCategoryName("default.");
        category.setUser(user);
        category.setColor(Color.WHITE);

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse updateCategory(long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findCategoryByIdAndUser(categoryId, UserUtils.authedUser())
                .orElseThrow(() -> new ResourceNotFoundException("Category could not be found"));

        if (categoryRequest.getCategoryName() != null && !categoryRequest.getCategoryName().isEmpty()) {
            category.setCategoryName(categoryRequest.getCategoryName());
        }

        if (categoryRequest.getColor() != null) {
            category.setColor(categoryRequest.getColor());
        }

        return Mapper.mapCategoryResponse(categoryRepository.save(category));
    }
}
