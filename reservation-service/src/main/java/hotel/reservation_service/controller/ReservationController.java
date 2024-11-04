package hotel.reservation_service.controller;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
