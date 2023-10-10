package com.dominiknagy.tinyexpense.TinyExpense.requests;

import lombok.Data;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccountRequest {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email address must be valid")
    private String email;

    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[0-9]).*$",
            message = "Password must contain at least one special character and one digit")
    private String password;
}
