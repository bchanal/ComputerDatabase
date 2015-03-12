package com.excilys.cdb.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.util.DateUtil;
import com.excilys.cdb.util.Util;

public class DateValidation implements ConstraintValidator<Date, String> {

    @Autowired
    DateUtil dateUtil;
    
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
        if (Util.checkDate(value, dateUtil.getDatePattern(), dateUtil.getDateRegex()) == null) {
            return false;
        }

        return true;
    }

}
