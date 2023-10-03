package com.autthServer.authServer;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class JwtTokenProvider {
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

//    generate JWT token
    public String generateToken(Authentication authentication){
        String username=authentication
    }
}
