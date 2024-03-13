package com.llav3ji2019.application.applicationmanager.public_interface.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User findByUsername(String username);

    User save(User user);

    UserDetailsService userDetailsService();

    void addRoleToUser(String username, RoleName role);
}
