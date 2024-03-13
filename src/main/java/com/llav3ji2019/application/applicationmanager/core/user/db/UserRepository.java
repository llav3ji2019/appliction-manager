package com.llav3ji2019.application.applicationmanager.core.user.db;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
