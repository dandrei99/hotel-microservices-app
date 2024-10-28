package hotel.user_service.controller;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{role-name}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("role-name") String roleName){
        RoleDto roleDto = roleService.getRoleNyName(roleName);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
}
