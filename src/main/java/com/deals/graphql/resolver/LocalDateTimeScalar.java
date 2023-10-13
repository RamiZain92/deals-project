package com.deals.graphql.resolver;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Component;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Component
public class LocalDateTimeScalar extends GraphQLScalarType{

	public LocalDateTimeScalar() {
		super("LocalDateTime", "Local Date Time",  new Coercing() {

			@Override
			public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
				SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
				LocalDateTime date = (LocalDateTime)dataFetcherResult; 
				return formatter.format(Date.from(date.toInstant(ZoneOffset.UTC)));
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
