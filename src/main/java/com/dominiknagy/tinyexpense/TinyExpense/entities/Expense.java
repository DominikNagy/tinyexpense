package com.dominiknagy.tinyexpense.TinyExpense.entities;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Color;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private String expenseDescription;
    private Double amount;
    private Currency currency;
    private LocalDateTime dateTime;
    private Color color;
    private Timestamp createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
