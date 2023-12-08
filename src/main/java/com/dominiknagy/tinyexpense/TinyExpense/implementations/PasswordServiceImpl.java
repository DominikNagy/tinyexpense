package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Account;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.Password;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.PasswordRepository;
import com.dominiknagy.tinyexpense.TinyExpense.services.PasswordService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.SaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepository passwordRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordServiceImpl(PasswordRepository passwordRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passwordRepository = passwordRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createHashedPassword(String passToHash, Account account) {
        Password password = new Password();
        String salt = bCryptPasswordEncoder.encode(SaltGenerator.generateSalt());
        String hashedPassword = BCrypt.hashpw(passToHash, salt);

        password.setPassword(hashedPassword);
        password.setSaltValue(salt);
        password.setAccount(account);

        passwordRepository.save(password);
    }

    @Override
    public boolean authorizeUser(Account account, String password) {
        Password savedPassword = passwordRepository.findPasswordByAccount(account).orElse(null);
        if (savedPassword == null) return false;
        String hashedReceivedPassword = BCrypt.hashpw(password, savedPassword.getSaltValue());
        return hashedReceivedPassword.equals(savedPassword.getPassword());
    }
}
