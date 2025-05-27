package com.br.CEMP.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.CEMP.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    private final String issuer = "auth-cemp";

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getUsername())
                    .withClaim("roles", user.getRoles().name())
                    .withExpiresAt(generationExpirationAt())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            e.printStackTrace();
            return "Error while generating token";
        }
    }

    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

        }catch (JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }

    private Instant generationExpirationAt(){
        return LocalDateTime.now().plusDays(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
