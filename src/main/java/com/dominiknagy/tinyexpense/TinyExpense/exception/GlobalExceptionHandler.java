package com.dominiknagy.tinyexpense.TinyExpense.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler({PSQLException.class})
    public ResponseEntity<Object> handleDatabaseException(PSQLException e) {
        if (e.getMessage().contains("duplicate"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse("Email is already registered", HttpStatus.CONFLICT, OffsetDateTime.now()));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse("Something went wrong in the database", HttpStatus.INTERNAL_SERVER_ERROR, OffsetDateTime.now()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleBadRequestException(HttpMessageNotReadableException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("Bad credentials or user doesn't exist", HttpStatus.FORBIDDEN, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    //todo
    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<Object> handleSignatureException(SignatureException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(e.getMessage(), HttpStatus.FORBIDDEN, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    //todo
    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<Object> handleMalformedException(MalformedJwtException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("Token is malformed", HttpStatus.FORBIDDEN, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

    //todo
    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Object> handleMalformedException(ExpiredJwtException e) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse("Token is expired", HttpStatus.FORBIDDEN, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }
}


