package com.spyglass.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spyglass.filters.AuthorizationFilter;
import com.spyglass.models.Account;
import com.spyglass.services.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
	
	//@Value("${jwt.secret}")
	private String secret = "bruhwhydontitwork";
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/token/refresh")
	public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				log.trace("Refreshing Token");
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				
				String username = decodedJWT.getSubject();
				Account account = service.findByEmail(username);
			
				String access_token = JWT.create()
						.withSubject(account.getEmail())
						.withExpiresAt(new Date(System.currentTimeMillis() + 600 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.sign(algorithm);
				
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				return new ResponseEntity<>(tokens, HttpStatus.OK);
				
			} catch (JWTVerificationException e) {
				log.trace("Refresh token expired");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
