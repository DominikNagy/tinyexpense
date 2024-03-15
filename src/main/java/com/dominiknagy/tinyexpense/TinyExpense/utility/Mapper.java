package com.dominiknagy.tinyexpense.TinyExpense.utility;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Category;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.responses.CategoryResponse;
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
}
