package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User createUser(CreateUserRequest createUserRequest);
    User retrieveUser(String userId);
    User loginUser(String email, String password);
    UserDetailsService userDetailsService();
}
