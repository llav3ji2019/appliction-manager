package com.llav3ji2019.application.applicationmanager.rest.auth;

import com.llav3ji2019.application.applicationmanager.public_interface.auth.AuthServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.JwtAuthenticationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignInDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.auth.SignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
@Tag(name="Контроллер аутификации", description="Контроллер управляет процессом аутефикации пользователей")
public class AuthController {
    private final AuthServiceV1 authenticationService;

    @PostMapping("/sign-up")
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    public JwtAuthenticationDto signUp(
            @RequestBody @Parameter(description = "Необходимые данные для регистрации") SignUpDto dto
    ) {
        return authenticationService.signUp(dto);
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Вход в систему",
            description = "Позволяет пользователю войти в систему"
    )
    public JwtAuthenticationDto signIn(
            @RequestBody @Parameter(description = "Необходимые данные для входа в систему") SignInDto dto
    ) {
        return authenticationService.signIn(dto);
    }
}
