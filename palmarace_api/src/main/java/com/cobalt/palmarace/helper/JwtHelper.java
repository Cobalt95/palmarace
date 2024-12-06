package com.cobalt.palmarace.helper;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import com.cobalt.palmarace.constant.PalmaraceConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtHelper {
	
	public static String generateJwtToken(String username, String authorities) {
		
		String secretKey = System.getenv(PalmaraceConstants.JWT_SECRET_KEY) != null ?
				System.getenv(PalmaraceConstants.JWT_SECRET_KEY):
				PalmaraceConstants.JWT_SECRET_DEFAULT;
		SecretKey hashedSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		
		String jwtToken = Jwts.builder()
				.issuer(PalmaraceConstants.JWT_ISSUER)
				.subject("JWT Token")
				.claim("username", username)
				.claim("authorities", authorities)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + 3000000))
				.signWith(hashedSecretKey)
				.compact();
		
		return jwtToken;
	}

}
