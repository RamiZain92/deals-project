package com.cybersolution.fazeal.logistics.exception;

import com.cybersolution.fazeal.common.business.dto.GraphQLErrorResponse;
import com.cybersolution.fazeal.common.utils.AppConstants;
import com.cybersolution.fazeal.logistics.util.Messages;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphqlErrorHandler implements GraphQLErrorHandler {

	@Autowired
	public Messages messages;

	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> list) {
		return list.stream().map(this::getNested).collect(Collectors.toList());
	}

	private GraphQLError getNested(GraphQLError error) {
		if (error instanceof ExceptionWhileDataFetching) {
			ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
			return new CustomGraphQLExceptionHandler(exceptionError);
		} else if (error.getErrorType().equals(ErrorType.InvalidSyntax)) {
			return CustomGraphqlError.builder().errorCode(AppConstants.ERROR_INVALID_SYNTAX)
					.errorMessage(messages.get(AppConstants.ERROR_INVALID_SYNTAX))
					.errorDetail(new GraphQLErrorResponse(
							new SimpleDateFormat(AppConstants.SIMPLE_DATE_FORMAT).format(new Date()),
							error.getMessage()))
					.build();
		} else if (error.getErrorType().equals(ErrorType.ValidationError)) {
			return CustomGraphqlError.builder().errorCode(AppConstants.VALIDATION_FAILED)
					.errorMessage(messages.get(AppConstants.VALIDATION_FAILED))
					.errorDetail(new GraphQLErrorResponse(
							new SimpleDateFormat(AppConstants.SIMPLE_DATE_FORMAT).format(new Date()),
							error.getMessage()))
					.build();
		}
		return error;
	}
}
