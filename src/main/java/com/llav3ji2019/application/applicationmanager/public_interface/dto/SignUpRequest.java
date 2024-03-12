package com.llav3ji2019.application.applicationmanager.public_interface.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;

    private String email;

    private String password;
}
