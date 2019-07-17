package com.kashegypt.gateway.notification.jms.consumer.model.validator.annotation;

import com.kashegypt.gateway.notification.jms.consumer.model.validator.type.ICode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValueValidator implements ConstraintValidator<Enum, Object> {
    private Enum annotation;

    @Override
    public void initialize(Enum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Object valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            //For Enums implementing ICode interface
            if (ICode.class.isAssignableFrom(this.annotation.enumClass())) {
                //If Enum Id is required to be matched
                if (annotation.matchId()) {
                    //Integer should be passed while going for validating Enum Id
                    if (!(valueForValidation instanceof Number)) return false;
                    return Arrays.stream(enumValues).anyMatch(enumObj -> ((ICode) enumObj).getId() == Integer.valueOf(valueForValidation.toString()));
                } else
                    return Arrays.stream(enumValues).anyMatch(enumObj -> ((ICode) enumObj).getName().equalsIgnoreCase(valueForValidation.toString()));
            }
            // For all other Enums not implementing ICode
            return Arrays.stream(enumValues).anyMatch(enumObj -> (String.valueOf(valueForValidation).equalsIgnoreCase(enumObj.toString())));
        }

        return false;
    }
}
