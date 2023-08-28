package com.eduardo.newcadastro.infraestructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eduardo.newcadastro.domain.exception.BusinessException;
import com.eduardo.newcadastro.domain.model.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("new-cadastro")
					.withSubject(user.getLogin())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			
			return token;
		} catch (JWTCreationException e) {
			
			throw new BusinessException("Erro ao criar o token");
		}
		
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("new-cadastro")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			
			return "";
			
		}
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
