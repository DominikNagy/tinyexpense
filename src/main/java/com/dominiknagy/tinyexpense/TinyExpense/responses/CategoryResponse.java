package com.dominiknagy.tinyexpense.TinyExpense.responses;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private long id;
    private String name;
    private Color color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
