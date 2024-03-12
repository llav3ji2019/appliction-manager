package com.llav3ji2019.application.applicationmanager.core.user.db;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("update user u set u.role = :role where u.username = :username")
    void updateRoleByUsername(@Param("username") String username, @Param("role") String role);
}
