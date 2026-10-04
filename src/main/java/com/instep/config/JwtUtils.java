package com.instep.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private final Long accessTym;
	private final Long refreshTym;
	private final SecretKey secretKey;

	public JwtUtils(@Value("${jwt.access-exp}") Long accessTym, @Value("${jwt.refresh-exp}") Long refreshTym,
			@Value("${jwt.secret}") String secret) {

		this.accessTym = accessTym;
		this.refreshTym = refreshTym;
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	// TODO generateAccessToken
	public String generateAccessToken(String username, Long id) {
		return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + accessTym)).subject(username).claim("userId", id)
				.signWith(secretKey).compact();

	}

	// TODO generateRefreshToken
	public String generateRefreshToken(String username, Long id) {
		return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + refreshTym)).subject(username).claim("userId", id)
				.signWith(secretKey).compact();
	}

	// TODO generateNewAccessToken
	public String generateNewAccessToken(String token) {
		return generateAccessToken(extractUsername(token), extractUserId(token));

	}

	// TODO validateToken
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	// TODO extractUserId
	public Long extractUserId(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId",
				Long.class);
	}

	// TODO extractUsername
	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
	}

	 public long getAccessExpiration() {
	        return accessTym;
	    }

}
