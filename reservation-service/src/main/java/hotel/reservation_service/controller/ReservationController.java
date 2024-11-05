package hotel.reservation_service.controller;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<APIResponseDto> saveReservation(@RequestParam Long userId, @RequestParam Long roomId) {
        APIResponseDto apiResponseDto = reservationService.saveReservation(userId, roomId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/addHotelService")
    public ResponseEntity<APIResponseDto> addServiceToReservation(@RequestParam Long userId, @RequestParam Long serviceId){
        APIResponseDto apiResponseDto = reservationService.addServiceToReservation(userId, serviceId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
