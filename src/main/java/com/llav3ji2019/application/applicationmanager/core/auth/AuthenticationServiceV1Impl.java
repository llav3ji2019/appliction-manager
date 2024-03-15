package com.llav3ji2019.application.applicationmanager.core.auth;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.auth.AuthServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.JwtAuthenticationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignInDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignUpDto;
import com.llav3ji2019.application.applicationmanager.public_interface.mapper.AuthMapper;
import com.llav3ji2019.application.applicationmanager.public_interface.security.jwt.JwsServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.user.RoleServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceV1Impl implements AuthServiceV1 {
    private final UserServiceV1 userService;
    private final RoleServiceV1 roleService;
    private final JwsServiceV1 jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;

    @Override
    public JwtAuthenticationDto signUp(SignUpDto dto) {
        Role role = roleService.findByName(RoleName.USER);
        User user = authMapper.toEntity(dto, role);

        userService.save(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationDto(jwt);
    }

    @Override
    public JwtAuthenticationDto signIn(SignInDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );

        UserDetails user = userService.userDetailsService()
                .loadUserByUsername(dto.username());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationDto(jwt);
    }

    @Override
    public String getUsernameFromHeader(String authHeader) {
        return jwtService.extractUserName(authHeader.split(" ")[1].trim());
    }
}
