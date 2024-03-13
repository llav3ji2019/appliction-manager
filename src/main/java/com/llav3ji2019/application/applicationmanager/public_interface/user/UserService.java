package com.llav3ji2019.application.applicationmanager.public_interface.user;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User save(User user);

    UserDetailsService userDetailsService();

    void addRoleToUser(String username, RoleName role);

    List<UserDto> findAll();
}
