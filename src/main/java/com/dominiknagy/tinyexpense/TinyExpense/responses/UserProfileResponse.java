package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileResponse {
    private String email;
    private String name;
    private Currency currency;
    private LocalDateTime userRegistration;
    private LocalDateTime lastLogin;
    private LocalDateTime updatedAt;
}
