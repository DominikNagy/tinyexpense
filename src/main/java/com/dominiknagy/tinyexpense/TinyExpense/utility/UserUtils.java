package com.dominiknagy.tinyexpense.TinyExpense.utility;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {
    public static User authedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (User) userDetails;
    }
}
