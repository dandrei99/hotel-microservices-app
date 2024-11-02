package hotel.room_service.service.impl;

import hotel.room_service.dto.RoomDto;
import hotel.room_service.entity.Room;
import hotel.room_service.mapper.RoomMapper;
import hotel.room_service.repository.RoomRepository;
import hotel.room_service.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<RoomDto> findAll() {
       List<Room> allRooms = roomRepository.findAll();
       List<RoomDto> allRoomsDto = RoomMapper.mapToListRoomDto(allRooms);
       return allRoomsDto;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.getByRoomId(id);
        RoomDto roomDto = RoomMapper.mapToRoomDto(room);
        return roomDto;
    }
}
