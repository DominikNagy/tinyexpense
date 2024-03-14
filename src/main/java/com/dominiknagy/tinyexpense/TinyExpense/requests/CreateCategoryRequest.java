package com.dominiknagy.tinyexpense.TinyExpense.requests;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Color;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String categoryName;
    private Color color;
}
