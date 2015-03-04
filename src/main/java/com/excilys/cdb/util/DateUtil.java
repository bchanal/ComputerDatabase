package com.excilys.cdb.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    @Autowired
    private MessageSource messageSource;

    public String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("pattern", null, locale);
//        return "(19|20)[0-9][0-9]-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))(T|\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9])";

    }

    public String getDateRegex() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("regex", null, locale);
    }
    
}
