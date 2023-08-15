package com.programming.weatherserver.request;

import lombok.Data;

@Data
public class EmailMessageRequest {
    private String subject;
    private String message;
}
