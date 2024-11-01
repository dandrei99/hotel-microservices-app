package hotel.room_service.service.impl;

import hotel.room_service.dto.RoomDto;
import hotel.room_service.entity.Room;
import hotel.room_service.mapper.RoomMapper;
import hotel.room_service.repository.RoomRepository;
import hotel.room_service.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public RoomDto saveRoom(RoomDto roomDto) {
        Room room = RoomMapper.mapToRoom(roomDto);

        Room savedRoom = roomRepository.save(room);

        RoomDto savedRoomDto = RoomMapper.mapToRoomDto(savedRoom);

        return null;
    }
}
