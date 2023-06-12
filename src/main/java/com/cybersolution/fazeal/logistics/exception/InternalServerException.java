package com.cybersolution.fazeal.logistics.exception;

import com.cybersolution.fazeal.common.exception.GenericException;
import org.springframework.http.HttpStatus;

/**
 * See {@link GenericException}
 */
public class InternalServerException extends GenericException {

    public InternalServerException() {
        this(null, null, null);
    }

    public InternalServerException(Throwable throwable) {
        this(throwable, null, null);
    }

    public InternalServerException(Throwable throwable, String code, String message) {
        super(throwable, HttpStatus.INTERNAL_SERVER_ERROR, code, message);
    }
}
