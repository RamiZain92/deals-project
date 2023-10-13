package com.deals.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

/**
 * The standard error object structure with error code and message.
 * {@link ErrorDto.code} must be a unique string to distinguish from other error codes.
 * {@link ErrorDto.message} can be description representation explain the error.
 *
 * This ErrorDto is usually implicitly build from exception classes, see {@link GenericException}
 * Example: The below is the example error json
 * {
 *   code: "ERR_INVALID_ID",
 *   message: "The provided request is invalid."
 * }
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
}
