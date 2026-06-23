package com.krishna.project.HouZing.error;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;

    public ErrorResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }
}
