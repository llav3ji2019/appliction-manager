package com.llav3ji2019.application.applicationmanager.public_interface.application;

import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface ApplicationService {
    void saveApplication(ApplicationDto dto, ApplicationStatus status);

    ApplicationDto getApplication(String id);

    void updateStatus(UUID id, ApplicationStatus status);

    Page<ApplicationDto> getAllApplicationsByUser(
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
