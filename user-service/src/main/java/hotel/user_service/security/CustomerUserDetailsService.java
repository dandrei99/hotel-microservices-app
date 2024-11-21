package hotel.user_service.security;

import hotel.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        hotel.user_service.entity.User user = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(user.getEmail()) // Use email as the username
                .password(user.getPassword())
                .roles(user.getUserRole().getRoleName())
                .build();
    }
}
