package com.llav3ji2019.application.applicationmanager.public_interface.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Schema(description = "Сущность пользователя")
public class UserDto {
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Логин пользователя")
    private String username;

    @EqualsAndHashCode.Exclude
    @Schema(description = "Коллекция с ролями пользователя")
    private Set<String> roles;
}
