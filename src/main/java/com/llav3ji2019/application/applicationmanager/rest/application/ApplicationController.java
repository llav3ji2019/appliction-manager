package com.llav3ji2019.application.applicationmanager.rest.application;

import com.llav3ji2019.application.applicationmanager.public_interface.application.ApplicationServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.auth.AuthServiceV1;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationIdDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.application.ApplicationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications/v1")
@Tag(name = "Контролер заявок", description = "Позволяет управлять заявками, которые создают пользователи")
public class ApplicationController {
    private static final String HEADER_NAME = "Authorization";

    private final ApplicationServiceV1 applicationService;
    private final AuthServiceV1 authService;

    @PostMapping("/create/main")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Создание заявки",
            description = "Позволяет позволяет обычному пользователю создать заявку"
    )
    public ResponseEntity<Object> createApplication(
            @RequestBody @Parameter(description = "Необходимая информация для создания заявки") ApplicationDto dto,
            @RequestHeader(HEADER_NAME) @Parameter(description = "Значение, которое хранится в заголовке с именем HEADER_NAME") String authHeader
    ) {
        dto.setUsername(authService.getUsernameFromHeader(authHeader));
        applicationService.saveApplication(dto, ApplicationStatus.NOT_SENT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create/draft")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Создание черновика",
            description = "Позволяет позволяет обычному пользователю создать черновик"
    )
    public ResponseEntity<Object> createDraft(
            @RequestBody @Parameter(description = "Необходимая информация для создания черновика") ApplicationDto dto
    ) {
        applicationService.saveApplication(dto, ApplicationStatus.DRAFT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/user/get/all")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Получение заявок",
            description = "Позволяет обычному пользователю узнать состояние всех его заявок"
    )
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByUser(
            @RequestParam(name = "page", defaultValue = "0") @Parameter(description = "Номер страницы") int pageNumber,
            @RequestParam(name = "sortOrder", required = false) @Parameter(description = "Метод сортировки", example = "ASC") Sort.Direction sortOrder,
            @RequestHeader(HEADER_NAME) @Parameter(description = "Значение, которое хранится в заголовке с именем HEADER_NAME") String authHeader
    ) {
        String username = authService.getUsernameFromHeader(authHeader);
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUsername(
                username,
                pageNumber,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/draft")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Обновление данных заявки",
            description = "Позволяет обновить обычному пользователю данные заявки перед отправкой"
    )
    public ResponseEntity<Object> updateApplicationData(
            @RequestBody @Parameter(description = "Необходимая информация для обновления заявки") ApplicationDto dto
    ) {
        applicationService.updateApplicationData(dto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/sent/application")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Отправка заявки",
            description = "Позволяет отправить заявку к оператору обычному пользователю"
    )
    public ResponseEntity<Object> sendApplication(
            @RequestBody @Parameter(description = "Необходимая информация для отправки заявки") ApplicationIdDto dto
    ) {
        applicationService.sendApplication(dto.getApplicationId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/operator/get/all")
    @PreAuthorize("hasAuthority('OPERATOR')")
    @Operation(
            summary = "Получение заявок",
            description = "Позволяет оператору узнать заявоки отправленные пользователя"
    )
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByOperator(
            @RequestParam(name = "page", defaultValue = "0") @Parameter(description = "Номер страницы") int pageNumber,
            @RequestParam(name = "username") @Parameter(description = "Логин пользователя") String username,
            @RequestParam(name = "sortOrder", required = false) @Parameter(description = "Метод сортировки", example = "ASC") Sort.Direction sortOrder
    ) {
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUsernameAndStatus(
                username,
                ApplicationStatus.SENT,
                pageNumber,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('OPERATOR')")
    @Operation(
            summary = "Получение заявки",
            description = "Позволяет оператору узнать про заявоку по id"
    )
    public ResponseEntity<ApplicationDto> getApplication(@PathVariable @Parameter(description = "ID заявки") String id) {
        return ResponseEntity.ok(applicationService.getApplication(id));
    }

    @PutMapping("/accept/application")
    @PreAuthorize("hasAuthority('OPERATOR')")
    @Operation(
            summary = "Приём заявки",
            description = "Позволяет оператору одобрить заявку"
    )
    public ResponseEntity<Object> acceptApplication(
            @RequestBody @Parameter(description = "Необходимая информация для одобрения заявки") ApplicationIdDto dto
    ) {
        applicationService.updateStatus(dto.getApplicationId(), ApplicationStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/deny/application")
    @PreAuthorize("hasAuthority('OPERATOR')")
    @Operation(
            summary = "Отказ в заявке",
            description = "Позволяет оператору отказать в заявке"
    )
    public ResponseEntity<Object> denyApplication(
            @RequestBody @Parameter(description = "Необходимая информация для отказа по заявке") ApplicationIdDto dto
    ) {
        applicationService.updateStatus(dto.getApplicationId(), ApplicationStatus.DENIED);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/admin/get/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
            summary = "Получение заявок",
            description = "Позволяет администратору узнать заявоки с конкретным статусом"
    )
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByAdmin(
            @RequestParam(name = "username") @Parameter(description = "Логин пользователя") String username,
            @RequestParam(name = "status") @Parameter(description = "Статус заявок", example = "SENT") ApplicationStatus status,
            @RequestParam(name = "page", defaultValue = "0") @Parameter(description = "Номер страницы") int pageNumber,
            @RequestParam(name = "sortOrder", required = false) @Parameter(description = "Метод сортировки", example = "ASC") Sort.Direction sortOrder
    ) {
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUsernameAndStatus(
                username,
                status,
                pageNumber,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }
}
