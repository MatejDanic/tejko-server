package com.tejko.models.general.payload.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class DateIntervalRequest {

    @NotBlank
    private LocalDateTime startDate;

    @NotBlank
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}