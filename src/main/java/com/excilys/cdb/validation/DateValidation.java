package com.excilys.cdb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.cdb.model.Language;
import com.excilys.cdb.util.Util;

public class DateValidation implements ConstraintValidator<Date, String> {

    @Override
    public void initialize(Date constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context, Language lang) {
        if (value == null) {
            return false;
        }
        if (value.isEmpty()) {
            return false;
        }

        if (lang == Language.FRENCH) {
            if (Util.checkDateFr(value) == null) {
                return false;
            }
        }
        else{
            if (Util.checkDateEn(value) == null) {
                return false;
            }
            
        }
        return true;
    }

}
