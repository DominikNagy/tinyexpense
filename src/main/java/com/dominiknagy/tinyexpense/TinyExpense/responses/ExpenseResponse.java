package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {
    private long id;
    private String expenseDescription;
    private double amount;
    private Currency currency;
    private Color color;
    private String categoryName;
    private Color categoryColor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
