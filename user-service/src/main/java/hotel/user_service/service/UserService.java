package hotel.user_service.service;

import hotel.user_service.dto.UserDto;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserDto getUserById(Long userId);

    UserDto getUserByEmail(String email);
}
