package com.dominiknagy.tinyexpense.TinyExpense.requests;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import lombok.Data;

import java.sql.Date;

@Data
public class CreateExpenseRequest {
    private long expenseCategoryId;
    private String expenseDescription;
    private Double amount;
    private Currency currency;
    private Date date;
    private Color color;
}
