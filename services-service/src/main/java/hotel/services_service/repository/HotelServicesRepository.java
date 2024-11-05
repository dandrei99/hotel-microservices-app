package hotel.services_service.repository;

import hotel.services_service.entity.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServicesRepository extends JpaRepository<HotelService, Long> {

    HotelService findByServiceId(Long serviceId);
}
