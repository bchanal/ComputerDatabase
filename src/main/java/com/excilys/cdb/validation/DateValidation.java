package com.excilys.cdb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.cdb.util.Util;

public class DateValidation implements ConstraintValidator<Date, String> {

    @Override
    public void initialize(Date constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.isEmpty()) {
            return false;
        }
        if (Util.checkDate(value) == null) {
            return false;
        }

        return true;
    }

}
