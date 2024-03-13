package com.llav3ji2019.application.applicationmanager.core.user;

import com.llav3ji2019.application.applicationmanager.core.user.db.RoleRepository;
import com.llav3ji2019.application.applicationmanager.core.user.db.entity.Role;
import com.llav3ji2019.application.applicationmanager.public_interface.dto.RoleName;
import com.llav3ji2019.application.applicationmanager.public_interface.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Error: Role is not found in db"));
    }
}
