package com.dominiknagy.tinyexpense.TinyExpense.requests;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateExpenseRequest {
    private long categoryId;
    private String expenseDescription;
    private Double amount;
    private Currency currency;
    private LocalDateTime dateTime;
    private Color color;
}
