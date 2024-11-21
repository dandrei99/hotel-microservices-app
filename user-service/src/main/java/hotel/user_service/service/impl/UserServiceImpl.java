package hotel.user_service.service.impl;

import hotel.user_service.dto.UserDto;
import hotel.user_service.entity.User;
import hotel.user_service.mapper.UserMapper;
import hotel.user_service.repository.UserRepository;
import hotel.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);

        //hash the password before saving
        String hashedPassword  = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = userMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findByUserId(userId);
        UserDto userDto = userMapper.mapToUserDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        UserDto userDto = userMapper.mapToUserDto(user);
        return userDto;
    }

}
