package com.eduardoaf.balance.mod_shared.infrastructure.auth;

import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(String username) {
        return getJwtToken(username);
    }

    private String getJwtToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUsernameByJwt(String jwtToken) {
        return getAllClaims(jwtToken, Claims::getSubject);
    }

    public Date getExpirationDateByJwt(String jwtToken) {
        return getAllClaims(jwtToken, Claims::getExpiration);
    }

    private  <T> T getAllClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromJwt(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromJwt(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateByJwt(token).before(new Date());
    }

    public Boolean isValidJwtByUsername(String jwtToken, String username) {
        final String extractedUsername = getUsernameByJwt(jwtToken);
        return (extractedUsername.equals(username) && !isTokenExpired(jwtToken));
    }
}