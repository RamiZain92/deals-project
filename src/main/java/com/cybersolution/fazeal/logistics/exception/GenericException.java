//package com.cybersolution.business.exception;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.util.StringUtils;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import graphql.ErrorType;
//import graphql.GraphQLError;
//import graphql.GraphqlErrorHelper;
//import graphql.language.SourceLocation;
//
///**
// * This is the template exception class to be used by all application which consumes platform module.
// * The are few highly used HTTP Status code specific exception implementations are provided from platform.
// * Few implementations are {@link InternalServerException}, {@link NotFoundException}, {@link BadRequestException }
// *
// * Applications are free to extend this exception class to define
// * any application specific exceptions(e.g. InventoryNotAvailableException).
// *
// * All exception instances must provide a unique error code along with error message(see {@link ErrorDto})
// * along with the HTTP Status code that is expected from the API in the error scenario.
// *
// * See {@link ExceptionAdvice} for how the exceptions are handled from the platform provided
// * default REST exception controller advice.
// */
//public class GenericException extends RuntimeException implements GraphQLError{
//
//    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//    private String errorCode = "ERR_GEN_SEV1";
//    private String errorMessage = "There was an error while processing this request.";;
//
//    // discouraging to create exception instance without any details
//    private GenericException() {
//        this(null, null, null, null);
//    }
//
//    protected GenericException(Throwable cause) {
//        this(cause, null, null, null);
//    }
//
//    protected GenericException(String errorCode, String errorMessage) {
//        this(null, null, errorCode, errorMessage);
//    }
//
//    public GenericException(HttpStatus status, String errorCode, String errorMessage) {
//        this(null, status, errorCode, errorMessage);
//    }
//
//    protected GenericException(Throwable cause, HttpStatus status, String errorCode, String errorMessage) {
//        super(cause);
//
//        if (status != null) {
//            this.status = status;
//        }
//
//        if (StringUtils.hasText(errorCode)) {
//            this.errorCode = errorCode;
//        }
//
//        if (StringUtils.hasText(errorMessage)) {
//            this.errorMessage = errorMessage;
//        }
//    }
//
//    public HttpStatus getStatus() {
//        return status;
//    }
//
//    public ErrorDto getError() {
//        return new ErrorDto(errorCode, errorMessage);
//    }
//    
//    @Override
//	@JsonIgnore
//	public List<SourceLocation> getLocations() {
//		return null;
//	}
//
//	@Override
//	@JsonIgnore
//	public ErrorType getErrorType() {
//		return null;
//	}
//	
//	@Override
//	@JsonIgnore
//	public String getMessage() {
//		return errorMessage;	
//	}
//	
//	@Override
//	@JsonIgnore
//	public List<Object> getPath() {
//        return null;
//    }
//
//	@Override
//	@JsonIgnore
//	public Map<String, Object> toSpecification() {
//        return GraphqlErrorHelper.toSpecification(this);
//    }
//
//	@Override
//	@JsonIgnore
//	public Map<String, Object> getExtensions() {
//        return null;
//    }
//}
