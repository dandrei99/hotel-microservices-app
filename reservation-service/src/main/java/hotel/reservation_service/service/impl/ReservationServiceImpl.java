package hotel.reservation_service.service.impl;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.dto.ReservationDto;
import hotel.reservation_service.dto.RoomDto;
import hotel.reservation_service.dto.UserDto;
import hotel.reservation_service.entity.Reservation;
import hotel.reservation_service.repository.ReservationRepository;
import hotel.reservation_service.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    WebClient webClient;

    @Override
    public APIResponseDto saveReservation(Long userId, Long roomId) {

        UserDto userDto = webClient.get()
                .uri("http://localhost:8080/api/users/" + userId)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        RoomDto roomDto = webClient.get()
                .uri("http://localhost:8081/api/rooms/" + roomId)
                .retrieve()
                .bodyToMono(RoomDto.class)
                .block();

        // Create and save a new Reservation
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(reservation);

        // Map to ReservationDto
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(savedReservation.getReservationId());
        reservationDto.setUserId(savedReservation.getUserId());
        reservationDto.setRoomId(savedReservation.getRoomId());
        reservationDto.setReservationStatus(String.valueOf(savedReservation.getReservationStatus()));
        reservationDto.setCreatedAt(savedReservation.getCreatedAt());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setReservation(reservationDto);
        return apiResponseDto;
    }
}
