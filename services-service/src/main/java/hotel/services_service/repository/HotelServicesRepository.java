package hotel.services_service.repository;

import hotel.services_service.entity.HotelServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServicesRepository extends JpaRepository<HotelServices, Long> {
}
