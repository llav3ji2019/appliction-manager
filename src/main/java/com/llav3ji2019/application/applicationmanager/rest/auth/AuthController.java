package com.llav3ji2019.application.applicationmanager.rest.auth;

import com.llav3ji2019.application.applicationmanager.core.auth.AuthenticationService;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.JwtAuthenticationResponse;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignInRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
