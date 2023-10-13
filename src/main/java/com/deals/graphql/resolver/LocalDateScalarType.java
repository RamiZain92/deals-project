package com.deals.graphql.resolver;
import com.deals.constants.AppConstants;
import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
