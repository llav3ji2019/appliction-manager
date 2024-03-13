package com.llav3ji2019.application.applicationmanager.public_interface.mapper;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .roles(convertToStingSet(entity.getRoles()))
                .build();
    }

    private static Set<String> convertToStingSet(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(RoleName::name)
                .collect(Collectors.toSet());
    }
}
