package com.dominiknagy.tinyexpense.TinyExpense.entities;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
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
    private Timestamp createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
