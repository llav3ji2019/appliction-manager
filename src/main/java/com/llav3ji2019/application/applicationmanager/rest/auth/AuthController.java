package com.llav3ji2019.application.applicationmanager.rest.auth;

import com.llav3ji2019.application.applicationmanager.core.auth.AuthenticationService;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.JwtAuthenticationResponse;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignInRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignUpRequest;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
