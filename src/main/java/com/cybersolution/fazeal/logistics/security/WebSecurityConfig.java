package com.cybersolution.fazeal.logistics.security;

import com.cybersolution.fazeal.logistics.security.services.UserDetailsServiceImpl;
import com.cybersolution.fazeal.logistics.security.jwt.AuthEntryPointJwt;
import com.cybersolution.fazeal.logistics.security.jwt.AuthTokenFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
     securedEnabled = true,
     jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  AuthEntryPointJwt unauthorizedHandler;

  @Value("${origin.domain.http}")
	private String httpDomain;
	@Value("${origin.domain.https}")
	private String httpsDomain;
	@Value("${origin.domain.httpip}")
	private String httpIpDomain;
	@Value("${origin.domain.httpsip}")
	private String httpsIpDomain;

	@Value("${origin.support}")
	private String support;

	@Value("${origin.help}")
	private String help;

	@Value("${origin.api.http}")
	private String httpApi;
	@Value("${origin.api.https}")
	private String httpsApi;
	@Value("${origin.api.httpsip}")
	private String httpsIpApi;
	@Value("${origin.api.httpip}")
	private String httpIpApi;

  @Value("${isDEVMode}")
  private boolean isDev = false;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        

        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers("/api/v1/**").permitAll()
        .antMatchers("/graphql/**").permitAll().antMatchers("/graphiql/**").permitAll()

            .anyRequest().authenticated().and().httpBasic().and().formLogin();
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

  }
  
  private static final String[] AUTH_WHITELIST = {
          // -- Swagger UI v2
          "/v2/api-docs",
          "/swagger-resources",
          "/swagger-resources/**",
          "/configuration/ui",
          "/configuration/security",
          "/swagger-ui.html",
          "/webjars/**",
          // -- Swagger UI v3 (OpenAPI)
          "/v3/api-docs/**",
          "/swagger-ui/**"
  };

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin(httpDomain);
	config.addAllowedOrigin(httpsDomain);
	config.addAllowedOrigin(httpsIpDomain);
	config.addAllowedOrigin(httpIpDomain);
	config.addAllowedOrigin(support);
	config.addAllowedOrigin(help);
	config.addAllowedOrigin(httpApi);
	config.addAllowedOrigin(httpsApi);
	config.addAllowedOrigin(httpIpApi);
	config.addAllowedOrigin(httpsIpApi);
	config.addAllowedOriginPattern("*");
    config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.PUT.name(), HttpMethod.POST.name(),
        HttpMethod.OPTIONS.name(), HttpMethod.DELETE.name()));
    config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    if(isDev) web.ignoring().antMatchers("/v2/api-docs", "/configuration/**", "/swagger-resources/**",
        "/swagger-ui.html", "/webjars/**", "/api-docs/**");
    web.httpFirewall(configureFirewall());
  }

  @Bean
  public HttpFirewall configureFirewall() {
      StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
      strictHttpFirewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
      return strictHttpFirewall;
  }

  @Bean
  public RequestRejectedHandler requestRejectedHandler() {
		return new HttpStatusRequestRejectedHandler();
	}
}
