package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateAccountRequest;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;

    @Autowired
    public AccountController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Account account = accountServiceImpl.createAccount(createAccountRequest);
        return ResponseEntity.created(URI.create("/account/" + account.getId())).body(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> retrieveAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountServiceImpl.retrieveAccount(accountId));
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginAccount() {
//
//    }
}
