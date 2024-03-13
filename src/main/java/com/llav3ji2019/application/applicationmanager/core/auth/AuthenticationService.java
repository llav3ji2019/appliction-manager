package com.llav3ji2019.application.applicationmanager.core.auth;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.JwtAuthenticationResponse;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignInRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.SignUpRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.user.RoleService;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserService;
import com.llav3ji2019.application.applicationmanager.core.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(roleService.findByName(RoleName.USER)))
                .build();

        userService.save(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}