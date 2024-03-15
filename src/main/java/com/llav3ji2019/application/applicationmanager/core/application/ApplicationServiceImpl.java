package com.llav3ji2019.application.applicationmanager.core.application;

import com.llav3ji2019.application.applicationmanager.core.application.db.ApplicationRepository;
import com.llav3ji2019.application.applicationmanager.core.application.db.entity.Application;
import com.llav3ji2019.application.applicationmanager.public_interface.application.ApplicationServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationStatus;
import com.llav3ji2019.application.applicationmanager.public_interface.exception.ApiException;
import com.llav3ji2019.application.applicationmanager.public_interface.mapper.ApplicationMapper;
import com.llav3ji2019.application.applicationmanager.public_interface.phone.PhoneValidatorV1;
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
public class ApplicationServiceImpl implements ApplicationServiceV1 {
    private static final String SORTING_FIELD = "creationTime";

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final PhoneValidatorV1 phoneValidator;

    @Value("${pagination.page-size}")
    private int pageSize;

    @Override
    public void updateApplicationData(ApplicationDto dto) {
        Application oldApplication = findById(dto.getId());

        ApplicationStatus oldStatus = oldApplication.getStatus();
        if (!oldStatus.equals(ApplicationStatus.DRAFT)) {
            throw new ApiException("Incorrect application status");
        }
        applicationRepository.save(applicationMapper.toEntity(dto, oldStatus));
    }

    @Override
    public void saveApplication(ApplicationDto dto, ApplicationStatus status) {
        dto.setPhoneNumber(phoneValidator.validatePhone(dto.getPhoneNumber()));

        Application application = applicationMapper.toEntity(dto, status);

        if (status.equals(ApplicationStatus.NOT_SENT)) {
            application.setCreationTime(LocalDate.now());
        }

        applicationRepository.save(application);
    }

    @Override
    public Page<ApplicationDto> getAllApplicationsByUsername(
            String username,
            int pageIndex,
            Sort.Direction sortOrder
    ) {
        Pageable pageable = createPageable(pageIndex, sortOrder);

        return applicationRepository.selectByUsername(username, pageable)
                .map(applicationMapper::toDto);
    }

    @Override
    public Page<ApplicationDto> getAllApplicationsByUsernameAndStatus(
            String username,
            ApplicationStatus status,
            int pageIndex,
            Sort.Direction sortOrder
    ) {
        Pageable pageable = createPageable(pageIndex, sortOrder);
        return applicationRepository.selectByUsernameAndStatus(status, username, pageable)
                .map(applicationMapper::toDto);
    }

    @Override
    public ApplicationDto getApplication(String id) {
        Application application = findById(UUID.fromString(id));

        if (!application.getStatus().equals(ApplicationStatus.SENT)) {
            throw new ApiException("No rights");
        }

        return applicationMapper.toDto(application);
    }

    @Override
    public void sendApplication(UUID id) {
        Application application = findById(id);
        ApplicationStatus oldStatus = application.getStatus();
        if (!oldStatus.equals(ApplicationStatus.DRAFT) && !oldStatus.equals(ApplicationStatus.NOT_SENT)) {
            throw new ApiException("Incorrect application status");
        }

        if (application.getStatus().equals(ApplicationStatus.DRAFT)) {
            application.setCreationTime(LocalDate.now());
        }
        application.setStatus(ApplicationStatus.SENT);
        applicationRepository.save(application);
    }

    @Override
    public void updateStatus(UUID id, ApplicationStatus status) {
        Application application = findById(id);
        ApplicationStatus oldStatus = application.getStatus();
        if (!oldStatus.equals(ApplicationStatus.SENT)) {
            throw new ApiException("Incorrect application status");
        }

        application.setStatus(status);
        applicationRepository.save(application);
    }

    private Application findById(UUID id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ApiException("Application with this id does not exists"));
    }

    private Pageable createPageable(int pageIndex, Sort.Direction sortOrder) {
        Sort sort = sortOrder == null ? Sort.unsorted() : Sort.by(sortOrder, SORTING_FIELD);
        return PageRequest.of(pageIndex, pageSize, sort);
    }
}
