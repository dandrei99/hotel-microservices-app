package hotel.reservation_service.repository;

import hotel.reservation_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {


}
