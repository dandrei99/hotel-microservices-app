package hotel.reservation_service.service;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.dto.ReservationDatesRequestDto;

public interface ReservationService {

    APIResponseDto saveReservation(Long userId, Long roomId, String token, ReservationDatesRequestDto reservationDates);

    APIResponseDto addServiceToReservation(Long userId, Long serviceId, String token);

    APIResponseDto getReservation(Long userId, String token);
}
