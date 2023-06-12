package com.cybersolution.fazeal.logistics.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponse {

    private String code;
    private String message;
}
