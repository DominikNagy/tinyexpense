package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Color;
import lombok.Data;

@Data
public class CategoryResponse {
    private long id;
    private String name;
    private Color color;
}
