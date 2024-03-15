package com.dominiknagy.tinyexpense.TinyExpense.utility;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;

public class Mapper {
    public static UserProfileResponse mapUserProfileResponse(UserProfile userProfile) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setCurrency(userProfile.getCurrency());
        userProfileResponse.setName(userProfile.getUser().getName());
        userProfileResponse.setUserRegistration(userProfile.getUser().getCreatedAt());
        userProfileResponse.setUpdatedAt(userProfile.getUser().getUpdatedAt());
        userProfileResponse.setEmail(userProfile.getUser().getEmail());
        userProfileResponse.setLastLogin(userProfile.getUser().getLastLogin());

        return userProfileResponse;
    }

    public static CategoryResponse mapCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setColor(category.getColor());
        categoryResponse.setName(category.getCategoryName());
        categoryResponse.setUpdatedAt(category.getUpdatedAt());
        categoryResponse.setCreatedAt(category.getCreatedAt());

        return categoryResponse;
    }

    public static ExpenseResponse mapExpenseResponse(Expense expense) {
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setId(expense.getId());
        expenseResponse.setExpenseDescription(expense.getExpenseDescription());
        expenseResponse.setDateTime(expense.getDateTime());
        expenseResponse.setCurrency(expense.getCurrency());
        expenseResponse.setAmount(expense.getAmount());
        expenseResponse.setColor(expense.getColor());
        expenseResponse.setCategoryName(expense.getCategory().getCategoryName());
        expenseResponse.setCategoryColor(expense.getCategory().getColor());

        return expenseResponse;
    }

    public static Expense mapExpenseRequest(CreateExpenseRequest expenseRequest, Category category) {
        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setUser(UserUtils.authedUser());
        expense.setExpenseDescription(expenseRequest.getExpenseDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setColor(expenseRequest.getColor());
        expense.setCurrency(expenseRequest.getCurrency());
        expense.setDateTime(expenseRequest.getDateTime());

        return expense;
    }
}
