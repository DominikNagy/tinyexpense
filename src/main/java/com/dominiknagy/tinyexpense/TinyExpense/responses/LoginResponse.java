package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {
    private UserProfileResponse userProfile;
    private String token;
}
