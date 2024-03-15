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
public class ApplicationIdDto {
    @Schema(description = "Идентификатор заявки", example = "$2a$12$pK2jp9Z5e1nDLGmDXHD0D.9ssH0C5wI56.0VMv7FxV/AKBXQ.TFPi")
    @JsonProperty("application_id")
    private UUID applicationId;
}
