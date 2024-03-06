package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import lombok.Data;

@Data
public class LoginResponse {
    private Account account;
    private String token;
}
