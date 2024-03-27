package com.dominiknagy.tinyexpense.TinyExpense.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String error;
    private HttpStatus status;
    private OffsetDateTime timestamp;
}
