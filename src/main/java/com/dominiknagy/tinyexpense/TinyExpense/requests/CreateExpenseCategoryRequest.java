package com.dominiknagy.tinyexpense.TinyExpense.requests;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Color;
import lombok.Data;

@Data
public class CreateExpenseCategoryRequest {
    private String expenseCategoryName;
    private Color color;
}
