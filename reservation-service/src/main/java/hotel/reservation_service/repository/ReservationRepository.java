package hotel.reservation_service.repository;

import hotel.reservation_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    public Optional<Reservation> findByUserId(Long userId);

    public List<Reservation> findByRoomId(Long roomId);
}
