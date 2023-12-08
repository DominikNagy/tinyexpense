package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateAccountRequest;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
        if (account == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.created(URI.create("/account/" + account.getId())).body(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> retrieveAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountServiceImpl.retrieveAccount(accountId));
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        String[] credentials = decodedCredentials.split(":", 2); // Assuming username and password are separated by ":"

        String userEmail = credentials[0];
        String userPassword = credentials[1];

        Account account = accountServiceImpl.loginUser(userEmail, userPassword);

        if (account != null) return ResponseEntity.ok(account);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
