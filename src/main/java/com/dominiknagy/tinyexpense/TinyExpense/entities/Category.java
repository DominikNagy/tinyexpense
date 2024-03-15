package com.dominiknagy.tinyexpense.TinyExpense.entities;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String categoryName;
    private Color color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
