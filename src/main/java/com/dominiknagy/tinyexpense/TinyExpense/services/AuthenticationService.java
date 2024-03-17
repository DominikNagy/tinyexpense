package com.dominiknagy.tinyexpense.TinyExpense.services;

import com.dominiknagy.tinyexpense.TinyExpense.requests.LoginUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.LoginResponse;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ProfileService profileService;

    public LoginResponse login(String authorizationHeader) {

        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        String[] credentials = decodedCredentials.split(":", 2);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail(credentials[0]);
        loginUserRequest.setPassword(credentials[1]);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));
        var user = userService.retrieveUser(loginUserRequest.getEmail());
        var jwt = jwtService.generateToken(user);

        userService.lastLoginUpdate(user);
        UserProfileResponse userProfileResponse = profileService.retrieveUserProfileByEmail(user.getEmail());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwt);
        loginResponse.setUserProfile(userProfileResponse);

        return loginResponse;
    }
}
