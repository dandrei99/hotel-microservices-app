package hotel.room_service.controller;

import hotel.room_service.dto.RoomDto;
import hotel.room_service.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> saveRoom(@RequestBody RoomDto roomDto){
        RoomDto savedRoom = roomService.saveRoom(roomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}
