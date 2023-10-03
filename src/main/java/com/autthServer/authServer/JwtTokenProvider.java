package com.autthServer.authServer;

import org.springframework.beans.factory.annotation.Value;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
public class JwtTokenProvider {
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

}
