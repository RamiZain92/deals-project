package com.deals.configurations;

import java.util.Locale;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor messageSourceAccessor;

    public Messages() {
    }

    @PostConstruct
    private void init() {
        this.messageSourceAccessor = new MessageSourceAccessor(this.messageSource);
    }

    public String get(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSourceAccessor.getMessage(code, locale);
    }
}
