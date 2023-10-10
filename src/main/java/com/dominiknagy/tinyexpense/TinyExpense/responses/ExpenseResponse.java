package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Color;
import com.dominiknagy.tinyexpense.TinyExpense.entities.Currency;
import lombok.Data;

import java.sql.Date;

@Data
public class ExpenseResponse {
    private long id;
    private String expenseDescription;
    private double amount;
    private Currency currency;
    private Date date;
    private Color color;
    private String categoryName;
    private Color categoryColor;
}
