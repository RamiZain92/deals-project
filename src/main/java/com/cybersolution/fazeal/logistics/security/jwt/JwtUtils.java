package com.cybersolution.fazeal.logistics.security.jwt;

import java.util.Date;
import java.util.Map;

import com.cybersolution.fazeal.logistics.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${ciui.app.jwtSecret}")
	private String jwtSecret;

	@Value("${ciui.app.jwtExpirationMs}")
	private String jwtExpirationMs;

	public String generateJwtToken(UserDetailsImpl userDetails) {
				return generateTokenFromUserDetails(userDetails);
			}
	public String generateTokenFromUsername(String username, String companyId) {
		return Jwts.builder().setSubject(username).addClaims(Map.of("username",username)).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String generateTokenFromUserDetails(UserDetailsImpl userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).addClaims(Map.of("username",userDetails.getUsername())).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public Pair<String, String> getUserNameAndCompanyIdFromJWTToken(String token) {
		var body =Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		return Pair.of(body.getSubject(), body.get("username").toString());
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
