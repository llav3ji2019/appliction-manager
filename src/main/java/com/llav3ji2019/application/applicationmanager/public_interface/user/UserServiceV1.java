package com.llav3ji2019.application.applicationmanager.public_interface.user;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceV1 {
    User findByUsername(String username);

    User save(User user);

    UserDetailsService userDetailsService();

    void addRoleToUser(RoleDto dto, RoleName role);

    List<UserDto> findAll();
}
