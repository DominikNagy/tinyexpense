package com.dominiknagy.tinyexpense.TinyExpense.services;


import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;


public interface ProfileService {
    UserProfileResponse retrieveUserProfileByEmail(String userEmail);
    UserProfileResponse retrieveUserProfile();
    UserProfileResponse createUserProfile(User user);
}
