package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.CategoryRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.CategoryService;
import com.dominiknagy.tinyexpense.TinyExpense.services.UserService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.Mapper;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category retrieveCategory(long categoryId) {
        return categoryRepository.findCategoryById(categoryId).orElseThrow();
    }

    @Override
    public CategoryResponse retrieveCategoryAsResponse(long categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId).orElseThrow();
        if (category.getUser() == UserUtils.authedUser())
           return Mapper.mapCategoryResponse(categoryRepository.findCategoryById(categoryId).orElseThrow());
        else throw new AuthorizationServiceException("User not authorized for this resource");
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
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setCategoryName(createCategoryRequest.getCategoryName());
        category.setUser(UserUtils.authedUser());
        category.setColor(createCategoryRequest.getColor());

        return Mapper.mapCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId).orElseThrow();
        if (category.getUser() == UserUtils.authedUser())
            categoryRepository.deleteById(categoryId);
        else throw new AuthorizationServiceException("User not authorized");
    }
}
