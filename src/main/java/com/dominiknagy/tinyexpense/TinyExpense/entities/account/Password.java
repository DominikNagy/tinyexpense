package com.dominiknagy.tinyexpense.TinyExpense.entities.account;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String saltValue;

    @OneToOne
    private Account account;
}
