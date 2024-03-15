package com.dominiknagy.tinyexpense.TinyExpense.responses.healthcheck;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatusResponse {
    private String status;
    private LocalDateTime serverTime;
}
