package com.cybersolution.fazeal.logistics.graphql.resolver;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DateScalarType extends GraphQLScalarType{

	public DateScalarType() {
		super("Date", "Date TIME", new Coercing() {

			@Override
			public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
				SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.SIMPLE_DATE_FORMAT, Locale.ENGLISH);
				Date date = (Date)dataFetcherResult; 
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
