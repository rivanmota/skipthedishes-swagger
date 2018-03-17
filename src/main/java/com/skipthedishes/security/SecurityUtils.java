package com.skipthedishes.security;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityUtils {
	
	public static String generateJWTToken(String principalUsername) {
		
		String token = Jwts.builder()
                .setSubject(principalUsername)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
		
		String header = SecurityConstants.TOKEN_PREFIX + token;
		return header;
	}
}
