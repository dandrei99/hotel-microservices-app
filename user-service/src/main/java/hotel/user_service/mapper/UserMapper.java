package hotel.user_service.mapper;
import hotel.user_service.dto.UserDto;
import hotel.user_service.entity.Role;
import hotel.user_service.entity.User;
import hotel.user_service.repository.RoleRepository;
import hotel.user_service.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;


    public User mapToUser(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());

        Role role = roleRepository.findByRoleName(userDto.getUserRole());
        user.setUserRole(role);
        user.setCreatedAt(userDto.getCreatedAt());
        return user;
    }
    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getUserRole().getRoleName(),
                user.getCreatedAt()
        );
    }


}
