package com.cybersolution.fazeal.logistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class GenericObjectException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String errorCode = "ERR_GEN_SEV1";
    private String errorMessage = "There was an error while processing this request.";;

    protected GenericObjectException(Throwable cause) {
        this(cause, null, null, null);
    }

    protected GenericObjectException(String errorCode, String errorMessage) {
        this(null, null, errorCode, errorMessage);
    }

    public GenericObjectException(HttpStatus status, String errorCode, String errorMessage) {
        this(null, status, errorCode, errorMessage);
    }

    protected GenericObjectException(Throwable cause, HttpStatus status, String errorCode, String errorMessage) {
        super(cause);

        if (status != null) {
            this.status = status;
        }

        if (StringUtils.hasText(errorCode)) {
            this.errorCode = errorCode;
        }

        if (StringUtils.hasText(errorMessage)) {
            this.errorMessage = errorMessage;
        }
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorDto getError() {
        return new ErrorDto(errorCode, errorMessage);
    }
}
