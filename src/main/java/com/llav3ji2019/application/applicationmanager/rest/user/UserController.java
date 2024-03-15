package com.llav3ji2019.application.applicationmanager.rest.user;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.user.UserDto;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v1")
@Tag(name="Контроллер для пользователей", description="Контроллер служит для управления пользователями и их данными")
public class UserController {
    private final UserServiceV1 userService;
    @GetMapping("/get/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
            summary = "Получение всех пльзователей",
            description = "Позволяет получить админу всхе пользователей, зарегистрированных в системе"
    )
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
