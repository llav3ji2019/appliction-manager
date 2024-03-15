package com.llav3ji2019.application.applicationmanager.public_interface.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleDto(@Schema(description = "Логин пользователя") String username) {
}
