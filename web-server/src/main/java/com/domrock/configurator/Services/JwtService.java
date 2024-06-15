package com.domrock.configurator.Services;

import com.domrock.configurator.Model.ConfigModel.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    String jwtSecretKey = "codigosupermegaultrasecretogamer2";

    Long jwtExpirationMs = 3600000L; // 1h

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, claims -> claims.get("userEmail", String.class));
    }

    public String extractCompanyCnpj(String token) {
        return extractClaim(token, claims -> {
            Map<String, Object> companyCnpjMap = claims.get("companyCnpj", Map.class);
            return companyCnpjMap != null ? (String) companyCnpjMap.get("cnpj") : null;
        });
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("permissionID", user.getPermission().getId());
        claims.put("userEmail", user.getEmail());
        claims.put("companyCnpj", user.getCompanyCnpj().getCnpj());
        return generateToken(claims, user);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration != null && expiration.before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String generateToken(Map<String, Object> claims, User user) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
