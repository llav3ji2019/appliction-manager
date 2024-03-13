package com.llav3ji2019.application.applicationmanager.public_interface.dto;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.RoleName;
import lombok.Data;

@Data
public class RoleRequest {
    private String username;
    private RoleName role;
}
