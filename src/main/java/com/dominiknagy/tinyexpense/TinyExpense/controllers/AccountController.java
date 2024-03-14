package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.UserServiceImpl;
import com.dominiknagy.tinyexpense.TinyExpense.responses.LoginResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final UserServiceImpl accountServiceImpl;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateUserRequest createUserRequest) {
        User user = accountServiceImpl.createUser(createUserRequest);
        if (user == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        log.info("New account created '" + createUserRequest.getName()+ "'");
        return ResponseEntity.created(URI.create("/account/" + user.getId())).body(user);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> retrieveAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountServiceImpl.retrieveUser(accountId));
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return ResponseEntity.ok().body(authenticationService.login(authorizationHeader));
    }
}
