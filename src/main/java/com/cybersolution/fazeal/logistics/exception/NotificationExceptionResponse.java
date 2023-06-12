package com.cybersolution.fazeal.logistics.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationExceptionResponse {
    private String code;
    private String exception;
    private String message;
}
