package hotel.reservation_service.controller;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.service.ReservationService;
import hotel.reservation_service.service.impl.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<APIResponseDto> saveReservation(@RequestParam Long userId, @RequestParam Long roomId, @RequestHeader HttpHeaders headers) {
        // Extract the Authorization header
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }

        // Extract the token by removing the "Bearer " prefix
        String token = authorizationHeader.substring(7);

        log.info("Extracted Authorization token: {}", token);
        APIResponseDto apiResponseDto = reservationService.saveReservation(userId, roomId, token);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/addHotelService")
    public ResponseEntity<APIResponseDto> addServiceToReservation(@RequestParam Long userId, @RequestParam Long serviceId, @RequestHeader HttpHeaders headers){
        // Extract the Authorization header
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }

        // Extract the token by removing the "Bearer " prefix
        String token = authorizationHeader.substring(7);

        log.info("Extracted Authorization token: {}", token);

        APIResponseDto apiResponseDto = reservationService.addServiceToReservation(userId, serviceId, token);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponseDto> getReservation(@PathVariable ("userId") Long userId, @RequestHeader HttpHeaders headers){
        log.info("Headers received: {}", headers);
        // Extract the Authorization header
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }

        // Extract the token by removing the "Bearer " prefix
        String token = authorizationHeader.substring(7);

        log.info("Extracted Authorization token: {}", token);

        APIResponseDto apiResponseDto = reservationService.getReservation(userId, token);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
