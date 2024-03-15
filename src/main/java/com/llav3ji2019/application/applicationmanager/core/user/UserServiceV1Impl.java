package com.llav3ji2019.application.applicationmanager.core.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.RoleRepository;
import com.llav3ji2019.application.applicationmanager.core.user.db.UserRepository;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.user.UserDto;
import com.llav3ji2019.application.applicationmanager.public_interface.exception.ApiException;
import com.llav3ji2019.application.applicationmanager.public_interface.mapper.UserMapper;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserServiceV1;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceV1Impl implements UserServiceV1, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException("Error: Username is not found in db"));
    }

    @Override
    @Transactional
    public void addRoleToUser(RoleDto dto, RoleName name) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new ApiException("No user found"));
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new ApiException("No role found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new HashSet<>());
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
