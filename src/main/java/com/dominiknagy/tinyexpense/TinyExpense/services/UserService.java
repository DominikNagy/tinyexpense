package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.PasswordChangeRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User createUser(CreateUserRequest createUserRequest);
    User retrieveUser(String userId);
    User retrieveUserByEmail(String userEmail);
    UserDetailsService userDetailsService();
    User lastLoginUpdate(User user);
    void passwordChange(PasswordChangeRequest passwordChangeRequest);
}
