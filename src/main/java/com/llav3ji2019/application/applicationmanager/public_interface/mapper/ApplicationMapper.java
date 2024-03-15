package com.llav3ji2019.application.applicationmanager.public_interface.mapper;

import com.llav3ji2019.application.applicationmanager.core.application.db.entity.Application;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public Application toEntity(ApplicationDto dto, ApplicationStatus status) {
        return Application.builder()
                .text(dto.getText())
                .username(dto.getUsername())
                .phoneNumber(dto.getPhoneNumber())
                .status(status)
                .build();
    }

    public ApplicationDto toDto(Application entity) {
        return ApplicationDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .username(entity.getUsername())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .build();
    }
}
