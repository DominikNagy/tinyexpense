package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Role;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.UserRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateUserRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.PasswordChangeRequest;
import com.dominiknagy.tinyexpense.TinyExpense.services.ProfileService;
import com.dominiknagy.tinyexpense.TinyExpense.services.UserService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileService profileService;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
            }
        };
    }

    @Override
    public User lastLoginUpdate(User user) {
        user.setLastLogin(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void passwordChange(PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findUserById(UserUtils.authedUser().getId()).orElseThrow();
        if (passwordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setName(createUserRequest.getName());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        user = userRepository.save(user);

        profileService.createUserProfile(user);

        return user;
    }

    @Override
    public User retrieveUser(String userEmail) {
        return userRepository.findUserByEmail(userEmail).orElse(null);
    }

    @Override
    public User retrieveUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail).orElse(null);
    }
}
