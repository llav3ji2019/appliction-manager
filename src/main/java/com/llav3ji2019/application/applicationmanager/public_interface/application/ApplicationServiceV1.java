package com.llav3ji2019.application.applicationmanager.public_interface.application;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface ApplicationServiceV1 {
    void sendApplication(UUID id);
    void updateApplicationData(ApplicationDto dto);

    void saveApplication(ApplicationDto dto, ApplicationStatus status);

    ApplicationDto getApplication(String id);

    void updateStatus(UUID id, ApplicationStatus status);

    Page<ApplicationDto> getAllApplicationsByUsername(
            String username,
            int page,
            Sort.Direction sortOrder
    );

    Page<ApplicationDto> getAllApplicationsByUsernameAndStatus(
            String username,
            ApplicationStatus status,
            int page,
            Sort.Direction sortOrder
    );
}
