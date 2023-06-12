package com.cybersolution.fazeal.logistics.custom.validation;

import com.cybersolution.fazeal.logistics.constants.AppConstants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordConstraint {
	 String message() default AppConstants.PASSWORD_NOT_EMPTY;
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}