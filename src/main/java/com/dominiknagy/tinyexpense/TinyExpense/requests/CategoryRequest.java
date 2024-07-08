package com.dominiknagy.tinyexpense.TinyExpense.requests;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import lombok.Data;

@Data
public class CategoryRequest {
    private String categoryName;
    private Color color;
}
