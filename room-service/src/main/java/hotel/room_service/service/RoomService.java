package hotel.room_service.service;

import hotel.room_service.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto saveRoom(RoomDto roomDto);

    List<RoomDto> findAll();
}
