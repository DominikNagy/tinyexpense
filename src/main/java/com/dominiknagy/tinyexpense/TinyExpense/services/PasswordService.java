package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;

public interface PasswordService {
    void createHashedPassword(String password, Account account);
    boolean authorizeUser(Account account, String password);
}
