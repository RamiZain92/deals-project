package com.cybersolution.fazeal.logistics.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.common.exception.GenericException;
import org.springframework.security.access.AccessDeniedException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class CustomGraphQLExceptionHandler extends ExceptionWhileDataFetching {

	private Throwable throwable;
	private String errorCode = "ERR_GEN_SEV1";
	private String errorMessage = "There was an error while processing this request.";
	private String timestamp;

	public CustomGraphQLExceptionHandler(ExceptionWhileDataFetching inner) {
		super(ExecutionPath.fromList(inner.getPath()), inner.getException(), null);
		this.throwable = inner.getException();
		if (this.throwable instanceof GenericException) {
			GenericException exception = (GenericException) this.throwable;
			this.errorCode = exception.getError().getCode();
			this.errorMessage = exception.getError().getMessage();
		} else if (this.throwable instanceof AccessDeniedException) {
			this.errorCode = AppConstants.ACCESS_DENIED_EXCEPTION;
			this.errorMessage = this.throwable.getMessage();
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getTimestamp() {
		return new SimpleDateFormat(AppConstants.SIMPLE_DATE_FORMAT).format(new Date());
	}

	@JsonIgnore
	@Override
	public List<Object> getPath() {
		return super.getPath();
	}

	@JsonIgnore
	@Override
	public String getMessage() {
		return throwable.getMessage();
	}

	@Override
	@JsonIgnore
	public Throwable getException() {
		return super.getException();
	}

	@JsonIgnore
	@Override
	public List<SourceLocation> getLocations() {
		return super.getLocations();
	}

	@JsonIgnore
	@Override
	public Map<String, Object> getExtensions() {
		return super.getExtensions();
	}

	@JsonIgnore
	@Override
	public ErrorType getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
