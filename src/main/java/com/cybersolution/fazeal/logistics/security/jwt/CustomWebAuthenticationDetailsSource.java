package com.cybersolution.fazeal.logistics.security.jwt;

import lombok.Data;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

@Data
public class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    private String token;

    public CustomWebAuthenticationDetailsSource(HttpServletRequest request) {
        super.buildDetails(request);
        this.token = request.getHeader("Authorization");
    }
}
