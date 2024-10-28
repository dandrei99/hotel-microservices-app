package hotel.user_service.service.impl;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.entity.Role;
import hotel.user_service.mapper.RoleMapper;
import hotel.user_service.repository.RoleRepository;
import hotel.user_service.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto getRoleNyName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        RoleDto roleDto = RoleMapper.mapToDto(role);
        return roleDto;
    }
}
