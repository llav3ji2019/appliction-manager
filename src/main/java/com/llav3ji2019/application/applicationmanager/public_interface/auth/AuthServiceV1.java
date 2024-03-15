package com.llav3ji2019.application.applicationmanager.public_interface.auth;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.JwtAuthenticationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignInDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignUpDto;

public interface AuthServiceV1 {
    JwtAuthenticationDto signUp(SignUpDto request);
    JwtAuthenticationDto signIn(SignInDto request);
    String getUsernameFromHeader(String authHeader);
}
