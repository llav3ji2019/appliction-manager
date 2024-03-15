package com.llav3ji2019.application.applicationmanager.rest.role;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name="Контроллер для ролей", description="Позволяет управлять пользовательскими ролями")
public class RoleController {
    private final UserServiceV1 userService;

    @PutMapping("/add/operator")
    @Operation(
            summary = "Добавление нового оператора",
            description = "Позволяет добавить администратору новую роль для пользователю"
    )
    public void addOperatorRole(
            @RequestBody @Parameter(description = "Логин пользователя с которым он регистрировался") RoleDto dto
    ) {
        userService.addRoleToUser(dto, RoleName.OPERATOR);
    }
}
