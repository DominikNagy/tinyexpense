package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private User user;
    private String token;
}
