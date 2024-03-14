package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Role;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.UserRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.LoginUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginResponse login(String authorizationHeader) {

        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        String[] credentials = decodedCredentials.split(":", 2); // Assuming username and password are separated by ":"

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(credentials[0]);
        loginUserRequest.setPassword(credentials[1]);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));
        var user = userRepository.findUserByEmail(loginUserRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return LoginResponse.builder().token(jwt).user(user).build();
    }

}
