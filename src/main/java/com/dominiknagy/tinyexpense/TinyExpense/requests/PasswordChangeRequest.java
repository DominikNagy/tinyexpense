package com.dominiknagy.tinyexpense.TinyExpense.requests;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
