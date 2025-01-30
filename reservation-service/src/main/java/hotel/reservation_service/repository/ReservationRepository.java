package hotel.reservation_service.repository;

import hotel.reservation_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    public Optional<Reservation> findByUserId(Long userId);

    public List<Reservation> findByRoomId(Long roomId);

    @Query("SELECT r FROM Reservation r WHERE r.userId = :userId AND r.checkIn = :checkIn AND r.checkOut = :checkOut")
    public Optional<Reservation> findByUserIdAndCheckInAndCheckOut(
            @Param("userId") Long userId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );

}
