package com.cybersolution.fazeal.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableRetry
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients({"com.cybersolution.fazeal.common"})
@EnableSpringHttpSession
//@EnableCaching
public class FazealLogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FazealLogisticsApplication.class, args);
	}

	@Bean
	CookieSerializer cookieSerializer() {
		DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
		defaultCookieSerializer.setCookieName("CUSTOMSESSIONID");
		defaultCookieSerializer.setUseHttpOnlyCookie(false);
		defaultCookieSerializer.setCookiePath("/");
		defaultCookieSerializer.setUseSecureCookie(true);
		return defaultCookieSerializer;
	}

	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository(new ConcurrentHashMap<>());
	}

}
