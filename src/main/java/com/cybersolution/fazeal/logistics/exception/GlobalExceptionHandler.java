package com.cybersolution.fazeal.logistics.exception;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.common.dto.ErrorDetails;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.common.exception.ResourceNotFoundException;
import com.cybersolution.fazeal.common.utils.AppConstants;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	Messages messages;

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> ConstraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				Arrays.stream(ex.getMessage().split(","))
						.map(err -> messages.get(StringUtils.substringAfter(StringUtils.deleteWhitespace(err), ":")))
						.collect(Collectors.toList()).toString(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

//	@SneakyThrows
//	@ExceptionHandler(HttpClientErrorException.class)
//	public ResponseEntity<Object> httpClientErrorException(HttpClientErrorException ex, WebRequest request) {
//		NotificationExceptionResponse exceptionResponse = new ObjectMapper().readValue(ex.getResponseBodyAsString(),
//				NotificationExceptionResponse.class);
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionResponse.getMessage(),
//				request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}

//	@SneakyThrows
//	@ExceptionHandler(HttpServerErrorException.class)
//	public ResponseEntity<Object> httpServerErrorException(HttpServerErrorException ex, WebRequest request) {
//		NotificationExceptionResponse exceptionResponse = new ObjectMapper().readValue(ex.getResponseBodyAsString(),
//				NotificationExceptionResponse.class);
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionResponse.getMessage(),
//				request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler({ GenericException.class })
	public ResponseEntity<ErrorDetails> handleException(GenericException ex, WebRequest request) {
		log.error("GenericException...", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getError().getMessage(),
				ex.getError().getCode());
		return new ResponseEntity<ErrorDetails>(errorDetails, ex.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDeniedExcpetionHandler(AccessDeniedException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
																	  WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.PARAMS_INVALID),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException ex,
																  WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.TYPE_MISMATCH),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<Object> jsonMappingExceptionHandling(HttpMessageNotReadableException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				messages.get(AppConstants.JSON_NOT_CORRECT) + ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(com.cybersolution.fazeal.common.exception.ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(com.cybersolution.fazeal.common.exception.ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ResourceNotFoundException.class.getName(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OptimisticLockException.class)
	public ResponseEntity<Object> optimisticLockException(OptimisticLockException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.DB_EXCEPTION),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<Object> conversionFailedException(ConversionFailedException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.DB_EXCEPTION),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.DB_EXCEPTION),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(SdkClientException.class)
	public ResponseEntity<Object> sdkClientExceptionException(SdkClientException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.AMAZON_CLIENT),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<Object> amazonServiceException(AmazonServiceException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), messages.get(AppConstants.AMAZON_SERVICE_ERROR),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
