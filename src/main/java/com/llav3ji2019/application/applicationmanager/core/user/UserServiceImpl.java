package com.llav3ji2019.application.applicationmanager.core.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.UserRepository;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import com.llav3ji2019.application.applicationmanager.public_interface.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Error: Username is not found in db"));
    }

    @Override
    public void updateRole(String username, String role) {
        repository.updateRoleByUsername(username, role);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s isn't found", username));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new HashSet<>());
    }
}
