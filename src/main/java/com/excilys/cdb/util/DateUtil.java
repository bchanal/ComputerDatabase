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

    }

    public String getDateRegex() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("regex", null, locale);
    }
    
}
