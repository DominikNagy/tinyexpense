package com.dominiknagy.tinyexpense.TinyExpense.entities;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private ExpenseCategory expenseCategory;

    private String expenseDescription;
    private Double amount;
    private Currency currency;
    private Date date;
    private Color color;
    private Timestamp createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
