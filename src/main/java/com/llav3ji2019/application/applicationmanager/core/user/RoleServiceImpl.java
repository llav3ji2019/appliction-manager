package com.llav3ji2019.application.applicationmanager.core.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.RoleRepository;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.role.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.exception.ApiException;
import com.llav3ji2019.application.applicationmanager.public_interface.user.RoleServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleServiceV1 {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ApiException("Error: Role is not found in db"));
    }
}
