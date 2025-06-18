package hotel.user_service.controller;

import hotel.user_service.dto.RoleDto;
import hotel.user_service.dto.UserDto;
import hotel.user_service.security.JwtUtil;
import hotel.user_service.service.RoleService;
import hotel.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/role/{role-name}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("role-name") String roleName){
        RoleDto roleDto = roleService.getRoleNyName(roleName);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        UserDto savedUserDto = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("getUserByEmail/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("userEmail") String email){
        UserDto userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("getUserFromToken")
    public ResponseEntity<UserDto> getUserFromToken(@RequestHeader HttpHeaders headers){
        // Extract the Authorization header
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }

        // Extract the token by removing the "Bearer " prefix
        String token = authorizationHeader.substring(7);

        String email = jwtUtil.extractEmail(token);
        UserDto userDto = userService.getUserByEmail(email);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
