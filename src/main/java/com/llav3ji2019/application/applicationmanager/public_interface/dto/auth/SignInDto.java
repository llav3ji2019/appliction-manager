package com.llav3ji2019.application.applicationmanager.public_interface.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность для входа в систему")
public record SignInDto(
        @Schema(description = "Логин") String username,
        @Schema(description = "Пароль") String password
) {
}
