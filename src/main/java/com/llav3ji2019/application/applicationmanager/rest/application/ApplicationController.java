package com.llav3ji2019.application.applicationmanager.rest.application;

import com.llav3ji2019.application.applicationmanager.core.jwt.JwtService;
import com.llav3ji2019.application.applicationmanager.public_interface.application.ApplicationService;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationIdDto;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.ApplicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
public class ApplicationController {
    private final ApplicationService applicationService;
    private final JwtService jwtService;

    @PostMapping("/create/main")
    public ResponseEntity<Object> createApplication(@RequestBody ApplicationDto dto) {
        applicationService.saveApplication(dto, ApplicationStatus.NOT_SENT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create/draft")
    public ResponseEntity<Object> createDraft(@RequestBody ApplicationDto dto) {
        applicationService.saveApplication(dto, ApplicationStatus.DRAFT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/user/get/all")
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByUser(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sortOrder", required = false) Sort.Direction sortOrder,
            @RequestHeader("Authorization") String jwt
    ) {
        String username = jwtService.extractUserName(jwt);
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUser(
                username,
                page,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin/get/all")
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByAdmin(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "status") ApplicationStatus status,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sortOrder", required = false) Sort.Direction sortOrder
    ) {
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUsernameAndStatus(
                username,
                status,
                page,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/operator/get/all")
    public ResponseEntity<Page<ApplicationDto>> getApplicationsByOperator(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "sortOrder", required = false) Sort.Direction sortOrder
    ) {
        Page<ApplicationDto> res = applicationService.getAllApplicationsByUsernameAndStatus(
                username,
                ApplicationStatus.SENT,
                page,
                sortOrder
        );
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApplicationDto> getApplication(@PathVariable String id) {
        return ResponseEntity.ok(applicationService.getApplication(id));
    }

    @PutMapping("/update/draft")
    public ResponseEntity<Object> updateDraft(@RequestBody ApplicationDto draftApplication) {
        applicationService.saveApplication(draftApplication, ApplicationStatus.DRAFT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/sent/application")
    public ResponseEntity<Object> sendApplication(@RequestBody ApplicationIdDto dto) {
        applicationService.updateStatus(dto.getApplicationId(), ApplicationStatus.SENT);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/accept/application")
    public ResponseEntity<Object> acceptApplication(@RequestBody ApplicationIdDto dto) {
        applicationService.updateStatus(dto.getApplicationId(), ApplicationStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/deny/application")
    public ResponseEntity<Object> denyApplication(@RequestBody ApplicationIdDto dto) {
        applicationService.updateStatus(dto.getApplicationId(), ApplicationStatus.DENIED);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
