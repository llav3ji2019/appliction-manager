package com.llav3ji2019.application.applicationmanager.public_interface.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность для регистрации")
public record SignUpDto(
        @Schema(description = "Логин")String username,
        @Schema(description = "Адрес электорной почты", example = "pavel@mail.ru") String email,
        @Schema(description = "Пароль") String password
) {
}
