package com.llav3ji2019.application.applicationmanager.rest.roles;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final UserService userService;

    @PutMapping("/update")
    public void updateUserRole(RoleRequest request) {
        userService.updateRole(request.getUsername(), request.getRole());
    }
}
