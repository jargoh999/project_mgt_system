package com.jargoh.project_management_system.config;//package com.jargoh.project_management_system.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.crypto.SecretKey;
//
//import java.util.Collection;
//import java.util.Date;
//
//import static com.jargoh.project_management_system.config.JwtConstants.EXPIRATION_TIME;
//import static com.jargoh.project_management_system.config.JwtConstants.SECRET;
//
//public class JwtProvider {
//    static SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
//    public static String generateToken(Authentication authentication){
//           String jwt = Jwts.builder().setIssuedAt(new Date())
//                   .setExpiration(new Date(new Date().getTime()+EXPIRATION_TIME))
//                   .claim("email",authentication.getName())
//                   .signWith(key)
//                   .compact();
//           return jwt;
//
//    }
//
//
//    public static String getEmailFromToken(String token){
//        token = token.substring(7);
//        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        return String.valueOf(claims.get("email"));
//    }
//
//
//
//
//}
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;

import static com.jargoh.project_management_system.config.JwtConstants.EXPIRATION_TIME;
import static com.jargoh.project_management_system.config.JwtConstants.SECRET;

public class JwtProvider {
    private static final Logger logger = Logger.getLogger(JwtProvider.class.getName());
    static SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(Authentication authentication) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
        logger.info("Generated JWT: " + jwt);
        return jwt;
    }

    public static String getEmailFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return String.valueOf(claims.get("email"));
        } catch (JwtException | IllegalArgumentException e) {
            logger.severe("Invalid JWT token: " + e.getMessage());
            return null;
        }
    }
}
