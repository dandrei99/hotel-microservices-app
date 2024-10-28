package hotel.user_service.repository;

import hotel.user_service.entity.Role;
import hotel.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
