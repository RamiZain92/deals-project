package com.cybersolution.fazeal.logistics.graphql.resolver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import org.springframework.stereotype.Component;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Component
public class LocalDateScalarType extends GraphQLScalarType {

	public LocalDateScalarType() {
		super("LocalDate", "LocalDate", new Coercing() {

			@Override
			public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(AppConstants.LOCAL_DATE_FORMAT);
				LocalDate date = (LocalDate) dataFetcherResult;
				return date.format(dateFormatter);
			}

			@Override
			public Object parseValue(Object input) throws CoercingParseValueException {
				return input;
			}

			@Override
			public Object parseLiteral(Object input) throws CoercingParseLiteralException {
				if (Objects.nonNull(input)) {
					if (input instanceof StringValue) {
						StringValue value = (StringValue) input;
						return value.getValue();
					}
				}
				return null;
			}
		});
	}

}
