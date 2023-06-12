package com.cybersolution.fazeal.logistics.util;

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
	
	@PostConstruct
	private void init() {
		messageSourceAccessor = new MessageSourceAccessor(messageSource); 
	}
	
	public String get(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSourceAccessor.getMessage(code,locale);
	}
	
}
