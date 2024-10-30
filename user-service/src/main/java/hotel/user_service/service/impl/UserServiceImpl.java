package hotel.user_service.service.impl;

import hotel.user_service.dto.UserDto;
import hotel.user_service.entity.User;
import hotel.user_service.mapper.UserMapper;
import hotel.user_service.repository.UserRepository;
import hotel.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {

        User user = userMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = userMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }
}
