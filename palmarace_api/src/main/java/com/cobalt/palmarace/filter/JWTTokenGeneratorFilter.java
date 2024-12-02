package com.cobalt.palmarace.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cobalt.palmarace.constant.PalmaraceConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(authentication != null) {
			Environment env = getEnvironment();
			if(env != null) {
				String secret = env.getProperty(PalmaraceConstants.JWT_SECRET_KEY, 
						PalmaraceConstants.JWT_SECRET_DEFAULT);
				SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
				
				String jwtToken = Jwts.builder()
				.issuer("Palmarace")
				.subject("JWT Token")
				.claim("username", "juste.leblanc@palmarace.com")
				.claim("authorities", List.of(new SimpleGrantedAuthority("user")).stream().map(
						GrantedAuthority::getAuthority).collect(Collectors.joining(","))
						)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + 3000000))
				.signWith(secretKey)
				.compact();
				
				response.setHeader(PalmaraceConstants.JWT_HEADER, jwtToken);
			}
//		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/register");
	}

}
