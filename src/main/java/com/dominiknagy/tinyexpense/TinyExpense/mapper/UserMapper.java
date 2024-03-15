package com.dominiknagy.tinyexpense.TinyExpense.mapper;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;

public class UserMapper {
    public static UserProfileResponse userProfileResponse(UserProfile userProfile) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setCurrency(userProfile.getCurrency());
        userProfileResponse.setName(userProfile.getUser().getName());
        userProfileResponse.setUserRegistration(userProfile.getUser().getCreatedAt());
        userProfileResponse.setUpdatedAt(userProfile.getUser().getUpdatedAt());
        userProfileResponse.setEmail(userProfile.getUser().getEmail());
        userProfileResponse.setLastLogin(userProfile.getUser().getLastLogin());

        return userProfileResponse;
    }
}
