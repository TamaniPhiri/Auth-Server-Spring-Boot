package com.autthServer.authServer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

//    generate JWT token
    public String generateToken(Authentication authentication){
        String username=authentication.getName();

        Date currentDate = new Date();

        Date expiredate=new Date(currentDate.getTime()+jwtExpirationDate);

        String token;
        token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

//    get username from jwt token
    public String getUsername(String token) {
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username;
        username = claims.getSubject();
        return username;
    }

//    validate jwt token
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT Token: {}",e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("JWT Token expired:{}",e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty:{}",e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("JWT is unsupported:{}",e.getMessage());
        }
        return false;
    }

    public String generateToken(Authentication authentication){
        String username=authentication.getName();

        Date currentDate=new Date();

        Date expireDate=new Date(currentDate.getTime()-jwtExpirationDate);
    }
}
