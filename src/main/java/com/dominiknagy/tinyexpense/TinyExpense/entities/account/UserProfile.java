package com.dominiknagy.tinyexpense.TinyExpense.entities.account;

import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Currency currency;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
