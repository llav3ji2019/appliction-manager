package com.llav3ji2019.application.applicationmanager.rest.role;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleRequest;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role/v1")
@RequiredArgsConstructor
public class RoleController {
    private final UserService userService;

    @PutMapping("/update")
    public void addUserRole(@RequestBody RoleRequest request) {
        userService.addRoleToUser(request.getUsername(), request.getRole());
    }

    @PutMapping("/operator")
    public void addOperatorRole(@RequestBody String username) {
        userService.addRoleToUser(username, RoleName.OPERATOR);
    }
}
