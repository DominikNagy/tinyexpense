package com.dominiknagy.tinyexpense.TinyExpense.services;


import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;


public interface ProfileService {
    UserProfileResponse retrieveUserProfile(String userEmail);
    UserProfileResponse createUserProfile(User user);
}
