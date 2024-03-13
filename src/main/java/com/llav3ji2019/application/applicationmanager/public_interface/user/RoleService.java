package com.llav3ji2019.application.applicationmanager.public_interface.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.RoleName;

public interface RoleService {
    Role findByName(RoleName name);
}
