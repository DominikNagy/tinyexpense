package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateAccountRequest;

public interface AccountService {
    Account createAccount(CreateAccountRequest createAccountRequest);
    Account retrieveAccount(String accountId);
}
