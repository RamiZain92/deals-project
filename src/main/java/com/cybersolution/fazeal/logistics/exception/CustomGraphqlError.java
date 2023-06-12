package com.cybersolution.fazeal.logistics.exception;

import com.cybersolution.fazeal.common.business.dto.GraphQLErrorResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;
import graphql.language.SourceLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomGraphqlError implements GraphQLError{
	
	private String errorCode;
	private String errorMessage;
	private GraphQLErrorResponse errorDetail;
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public GraphQLErrorResponse getErrorDetail() {
		return errorDetail;
	}
	
	@Override
	@JsonIgnore
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	@JsonIgnore
	public ErrorType getErrorType() {
		return null;
	}
	
	@Override
	@JsonIgnore
	public String getMessage() {
		return errorMessage;	
	}
	
	@Override
	@JsonIgnore
	public List<Object> getPath() {
        return null;
    }

	@Override
	@JsonIgnore
	public Map<String, Object> toSpecification() {
        return GraphqlErrorHelper.toSpecification(this);
    }

	@Override
	@JsonIgnore
	public Map<String, Object> getExtensions() {
        return null;
    }

}
