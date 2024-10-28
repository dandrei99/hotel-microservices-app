package hotel.user_service.mapper;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.entity.Role;

public class RoleMapper {

    public static Role mapToDomain(RoleDto roleDto){
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }

    public static RoleDto mapToDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
