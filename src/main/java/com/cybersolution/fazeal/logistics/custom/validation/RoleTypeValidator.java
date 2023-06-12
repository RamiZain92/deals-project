package com.cybersolution.fazeal.logistics.custom.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cybersolution.fazeal.logistics.constants.RoleType;

public class RoleTypeValidator implements ConstraintValidator<RoleTypeConstraint, String> {

	List<String> roleType = Stream.of(RoleType.values())
	                               .map(Enum::name)
	                               .collect(Collectors.toList());

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && roleType.contains(value);
	}

}
