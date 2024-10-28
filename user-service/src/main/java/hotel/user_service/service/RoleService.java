package hotel.user_service.service;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.entity.Role;

public interface RoleService {
    RoleDto getRoleNyName(String roleName);
}
