package hotel.room_service.repository;

import hotel.room_service.dto.RoomDto;
import hotel.room_service.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
