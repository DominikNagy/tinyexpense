package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<?> retrieveAccount() {
        return ResponseEntity.noContent().build();
    }
}
