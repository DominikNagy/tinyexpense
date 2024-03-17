package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.PasswordChangeRequest;
import com.dominiknagy.tinyexpense.TinyExpense.services.AuthenticationService;
import com.dominiknagy.tinyexpense.TinyExpense.services.ProfileService;
import com.dominiknagy.tinyexpense.TinyExpense.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        log.info("New account created '" + createUserRequest.getName()+ "'");
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.retrieveUserProfileByEmail(user.getEmail()));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> retrieveProfile() {
        return ResponseEntity.ok(profileService.retrieveUserProfile());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return ResponseEntity.ok().body(authenticationService.login(authorizationHeader));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        userService.passwordChange(passwordChangeRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
