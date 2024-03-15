package com.llav3ji2019.application.applicationmanager.public_interface.dto.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность заявки")
public class ApplicationDto {
    @Schema(description = "Идентификатор", example = "$2a$12$pK2jp9Z5e1nDLGmDXHD0D.9ssH0C5wI56.0VMv7FxV/AKBXQ.TFPi")
    private UUID id;
    @Schema(description = "Текст заявки")
    private String text;
    @Schema(description = "Логин пользователя")
    private String username;
    @JsonProperty(value = "phone_number")
    @Schema(description = "Номер телефона", example = "+7 846 231-60-14 доб. 139")
    private String phoneNumber;
    @Schema(description = "Статус заявки", example = "DRAFT")
    private ApplicationStatus status;
}
