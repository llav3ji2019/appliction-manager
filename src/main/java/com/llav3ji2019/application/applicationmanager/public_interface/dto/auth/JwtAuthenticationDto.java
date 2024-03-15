package com.llav3ji2019.application.applicationmanager.public_interface.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность для возврата JWT токена")
public class JwtAuthenticationDto {
    @Schema(description = "JWT токен")
    private String token;
}
