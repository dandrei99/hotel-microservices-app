package hotel.user_service.controller;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.dto.UserDto;
import hotel.user_service.service.RoleService;
import hotel.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/role/{role-name}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("role-name") String roleName){
        RoleDto roleDto = roleService.getRoleNyName(roleName);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        UserDto savedUserDto = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }
}
