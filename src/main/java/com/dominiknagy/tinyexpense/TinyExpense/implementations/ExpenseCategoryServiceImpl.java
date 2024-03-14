package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.ExpenseCategory;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.ExpenseCategoryRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final UserServiceImpl accountService;

    @Override
    public ExpenseCategory retrieveExpenseCategory(long expenseCategoryId) {
        return expenseCategoryRepository.findExpenseCategoryById(expenseCategoryId).orElse(null);
    }

    @Override
    public List<CategoryResponse> retrieveExpenseCategories(String accountId) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (ExpenseCategory expenseCategory :
                expenseCategoryRepository.findExpenseCategoriesByUser(accountService.retrieveUser(accountId))) {

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(expenseCategory.getId());
            categoryResponse.setName(expenseCategory.getCategoryName());
            categoryResponse.setColor(expenseCategory.getColor());

            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    @Override
    public ExpenseCategory createExpenseCategory(
            CreateExpenseCategoryRequest createExpenseCategoryRequest, String accountId) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setCategoryName(createExpenseCategoryRequest.getExpenseCategoryName());
        expenseCategory.setUser(accountService.retrieveUser(accountId));
        expenseCategory.setColor(createExpenseCategoryRequest.getColor());

        return expenseCategoryRepository.save(expenseCategory);
    }

    @Override
    public void deleteCategory(long categoryId) {
        expenseCategoryRepository.deleteById(categoryId);
    }
}
