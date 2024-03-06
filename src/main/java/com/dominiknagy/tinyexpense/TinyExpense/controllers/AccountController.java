package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateAccountRequest;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.AccountServiceImpl;
import com.dominiknagy.tinyexpense.TinyExpense.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Account account = accountServiceImpl.createAccount(createAccountRequest);
        if (account == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        log.info("New account created '" +createAccountRequest.getName()+ "'");
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
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccount(account);
        loginResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");

        if (account != null) return ResponseEntity.ok(loginResponse);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
