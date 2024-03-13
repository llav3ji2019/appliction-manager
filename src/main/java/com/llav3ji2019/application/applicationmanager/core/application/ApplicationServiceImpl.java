package com.llav3ji2019.application.applicationmanager.core.application;

import com.llav3ji2019.application.applicationmanager.core.application.db.ApplicationRepository;
import com.llav3ji2019.application.applicationmanager.core.application.db.entity.Application;
import com.llav3ji2019.application.applicationmanager.public_interface.application.ApplicationService;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationStatus;
import com.llav3ji2019.application.applicationmanager.public_interface.mapper.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    @Value("${pagination.page-size}")
    private int pageSize;
    @Override
    public void saveApplication(ApplicationDto dto, ApplicationStatus status) {
        Application application = applicationMapper.toEntity(dto, status);

        if (status.equals(ApplicationStatus.NOT_SENT)) {
            application.setCreationTime(LocalDate.now());
        }

        applicationRepository.save(application);
    }

    @Override
    public Page<ApplicationDto> getAllApplicationsByUser(
            String username,
            int page,
            Sort.Direction sortOrder
    ) {
        Pageable pageable = sortOrder == null ?
                PageRequest.of(page, pageSize):
                PageRequest.of(page, pageSize, Sort.by(sortOrder, "creationTime"));
        return applicationRepository.selectByUsername(
            username,
            pageable
        ).map(applicationMapper::toDto);
    }

    @Override
    public Page<ApplicationDto> getAllApplicationsByUsernameAndStatus(
            String username,
            ApplicationStatus status,
            int page,
            Sort.Direction sortOrder
    ) {
        Pageable pageable = sortOrder == null ?
                PageRequest.of(page, pageSize):
                PageRequest.of(page, pageSize, Sort.by(sortOrder, "creationTime"));
        applicationRepository.selectByUsernameAndStatus(
                status,
                username,
                pageable
        ).map(applicationMapper::toDto);

        return applicationRepository.selectByUsernameAndStatus(
                status,
                username,
                pageable
        ).map(applicationMapper::toDto);
    }

    @Override
    public ApplicationDto getApplication(String id) {
        Application application = findById(UUID.fromString(id));
        return applicationMapper.toDto(application);
    }

    @Override
    public void updateStatus(UUID id, ApplicationStatus status) {
        Application application = findById(id);
        if (application.getStatus().equals(ApplicationStatus.DRAFT)) {
            application.setCreationTime(LocalDate.now());
        }
        application.setStatus(status);
        applicationRepository.save(application);
    }

    private Application findById(UUID id) {
        return applicationRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
