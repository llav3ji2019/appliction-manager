package com.llav3ji2019.application.applicationmanager.public_interface.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwsServiceV1 {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
