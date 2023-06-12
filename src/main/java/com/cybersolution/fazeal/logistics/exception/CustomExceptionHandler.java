package com.cybersolution.fazeal.logistics.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.common.dto.ErrorDetails;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	Messages messages;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(
				new Date(), ex.getBindingResult().getFieldErrors().stream()
						.map(err -> messages.get(err.getDefaultMessage())).collect(Collectors.toList()).toString(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.API_NOT_FOUND),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.INVALID_PARAMS),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.TYPE_MISMATCH),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.INVALID_TYPE),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		List<String> params = new ArrayList<>();
		ex.getAllErrors().stream().forEach(error -> {
			String missed = error.toString().split("on field")[1];
			params.add(missed == null ? AppConstants.EMPTY : missed.split(":")[0]);
		});
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.BIND_MISMATCH) + " " + params,
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), messages.get(AppConstants.API_NOT_FOUND),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new Date(), ex.getMessage(),
				messages.get(AppConstants.VALIDATION_FAILED));
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}
