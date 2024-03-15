package com.llav3ji2019.application.applicationmanager.core.security.jwt;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.security.jwt.JwsServiceV1;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceV1Impl implements JwsServiceV1 {
    private static final int MILLIS_IN_SECOND = 1000;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token-duration}")
    private long tokenDuration;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder()
                .encodeToString(secret.getBytes());
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getEmail());
            claims.put("role", converToString(customUserDetails));
        }
        return generateToken(claims, userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + tokenDuration * MILLIS_IN_SECOND);

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private static String converToString(User user) {
        return String.join(
                " ",
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .map(RoleName::name)
                        .toList()
        );
    }
}
