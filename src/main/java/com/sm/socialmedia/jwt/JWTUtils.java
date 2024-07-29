package com.sm.socialmedia.jwt;

import org.springframework.stereotype.Component;

@Component
public class JWTUtils {
    private String secretKey;
    private String timeExpiration;
}
