package com.dominiknagy.tinyexpense.TinyExpense.responses.healthcheck;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class StatusResponse {
    private String status;
    private LocalDateTime serverTime;
}
