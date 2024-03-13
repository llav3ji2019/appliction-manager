package com.llav3ji2019.application.applicationmanager.core.application.db;

import com.llav3ji2019.application.applicationmanager.core.application.db.entity.Application;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    @Modifying
    @Query("update application a set a.status = :status where a.id = :id")
    Optional<Application> updateStatusById(@Param("id") UUID id, @Param("status") ApplicationStatus status);

    @Query("select a from application a where a.status = :status and a.username like CONCAT('%',:username,'%')")
    Page<Application> selectByUsernameAndStatus(
            @Param("status") ApplicationStatus status,
            @Param("username") String username,
            Pageable pageable
    );

    @Query("select a from application a where a.username = :username")
    Page<Application> selectByUsername(
            @Param("username") String username,
            Pageable pageable
    );
}
