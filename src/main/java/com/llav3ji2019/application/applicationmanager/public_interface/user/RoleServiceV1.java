package com.llav3ji2019.application.applicationmanager.public_interface.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;

public interface RoleServiceV1 {
    Role findByName(RoleName name);
}
