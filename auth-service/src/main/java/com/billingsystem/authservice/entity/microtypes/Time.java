package com.billingsystem.authservice.entity.microtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Time {
    private Timestamp time = Timestamp.from(Instant.now());

    public Time(Timestamp time) {
        if (time != null) {
            this.time = time;
        }
    }

    public LocalDateTime getTimeAsLocalDateTime() {
        return time.toLocalDateTime();
    }
}
