package hotel.user_service.repository;

import hotel.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    Optional<User> findByEmail(String email);
}
