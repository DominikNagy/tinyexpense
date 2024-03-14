package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.responses.healthcheck.StatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class HealthCheckController {

    @GetMapping("/status")
    public ResponseEntity<?> retrieveAccount() {
        StatusResponse statusResponse = StatusResponse.builder().status("RUNNING").serverTime(LocalDateTime.now()).build();
        return ResponseEntity.ok(statusResponse);
    }
}
