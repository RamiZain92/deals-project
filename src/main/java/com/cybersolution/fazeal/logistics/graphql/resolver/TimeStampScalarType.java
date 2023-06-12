package com.cybersolution.fazeal.logistics.graphql.resolver;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import org.springframework.stereotype.Component;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Component
public class TimeStampScalarType extends GraphQLScalarType{
	
	public TimeStampScalarType() {
		super("TimeStamp", "TimeStamp", new Coercing() {

			@Override
			public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
				SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.SIMPLE_DATE_FORMAT, Locale.ENGLISH);
				Timestamp date = (Timestamp)dataFetcherResult; 
				return formatter.format(date);
			}

			@Override
			public Object parseValue(Object input) throws CoercingParseValueException {
				return input;
			}

			@Override
			public Object parseLiteral(Object input) throws CoercingParseLiteralException {
				return Objects.isNull(input)? null : input.toString();
			}
			
		});

	}

}
