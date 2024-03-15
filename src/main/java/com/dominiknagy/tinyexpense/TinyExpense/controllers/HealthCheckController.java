package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.responses.healthcheck.StatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthCheckController {

    @GetMapping("/status")
    public ResponseEntity<?> retrieveAccount() {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatus("RUNNING");
        statusResponse.setServerTime(LocalDateTime.now());

        return ResponseEntity.ok(statusResponse);
    }
}
