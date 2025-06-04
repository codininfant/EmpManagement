package com.pms.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signkey = "pms";
    private static int expirationTime = 60 * 60 * 24; // 24 hours
    public static String generateJwtToken(Map<String, Object> claims) {
        String JwtToken = Jwts.builder().addClaims(claims).setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .signWith(SignatureAlgorithm.HS512, signkey).compact();
        return JwtToken;
    }
    public static Map<String, Object> parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(signkey).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
