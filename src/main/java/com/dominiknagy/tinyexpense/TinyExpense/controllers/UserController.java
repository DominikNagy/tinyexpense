package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.UserServiceImpl;
import com.dominiknagy.tinyexpense.TinyExpense.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl accountServiceImpl;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateUserRequest createUserRequest) {
        User user = accountServiceImpl.createUser(createUserRequest);
        if (user == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        log.info("New account created '" + createUserRequest.getName()+ "'");
        return ResponseEntity.created(URI.create("/account/" + user.getId())).body(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> retrieveAccount(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(accountServiceImpl.retrieveUser(userDetails.getUsername()));
        } else return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return ResponseEntity.ok().body(authenticationService.login(authorizationHeader));
    }
}
