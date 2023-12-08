package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.AccountRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateAccountRequest;
import com.dominiknagy.tinyexpense.TinyExpense.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordServiceImpl passwordService;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordServiceImpl passwordService) {
        this.accountRepository = accountRepository;
        this.passwordService = passwordService;
    }

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        if (accountRepository.findAccountByEmail(createAccountRequest.getEmail()).isPresent())
            return null;

        Account account = new Account();

        account.setEmail(createAccountRequest.getEmail());
        account.setName(createAccountRequest.getName());
        account = accountRepository.save(account);

        passwordService.createHashedPassword(createAccountRequest.getPassword(), account);

        return account;
    }

    @Override
    public Account retrieveAccount(String accountId) {
        return accountRepository.findAccountById(UUID.fromString(accountId)).orElse(null);
    }

    @Override
    public Account loginUser(String userEmail, String password) {
        Account account = accountRepository.findAccountByEmail(userEmail).orElse(null);
        if (account == null) return null;
        if (passwordService.authorizeUser(account, password)) return account;
        else return null;
    }
}
