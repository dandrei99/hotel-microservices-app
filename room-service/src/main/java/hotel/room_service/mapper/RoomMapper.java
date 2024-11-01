package hotel.room_service.mapper;

import hotel.room_service.dto.RoomDto;
import hotel.room_service.entity.Room;

public class RoomMapper {

    public static RoomDto mapToRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(room.getRoomId());
        roomDto.setRoomType(String.valueOf(room.getRoomType()));
        roomDto.setMaxOccupancy(room.getMaxOccupancy());
        roomDto.setDescription(room.getDescription());
        roomDto.setPricePerNight(room.getPricePerNight());
        roomDto.setAvailability(room.isAvailability());
        roomDto.setSurface(room.getSurface());
        return roomDto;
    }

    public static Room mapToRoom(RoomDto roomDto){
        Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setRoomType(Room.RoomType.valueOf(roomDto.getRoomType()));
        room.setMaxOccupancy(roomDto.getMaxOccupancy());
        room.setDescription(roomDto.getDescription());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setAvailability(roomDto.isAvailability());
        room.setSurface(roomDto.getSurface());
        return room;
    }
}
