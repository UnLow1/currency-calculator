package com.springmvc.currencyCalculatorApp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class nameConstraintValidator implements ConstraintValidator<name, String> {

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        return code.matches("[A-Z]+");
    }
}
