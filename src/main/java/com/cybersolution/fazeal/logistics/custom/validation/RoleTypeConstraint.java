package com.cybersolution.fazeal.logistics.custom.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cybersolution.fazeal.logistics.constants.AppConstants;

@Constraint(validatedBy = RoleTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleTypeConstraint {
    String message() default AppConstants.ROLE_TYPE_NOT_FROM_LIST;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
