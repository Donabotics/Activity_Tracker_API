package com.donatus.activity_tracker_api.entity;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private HttpStatus status;
    private String debugMessage;
    private LocalDateTime dateTime;
}
