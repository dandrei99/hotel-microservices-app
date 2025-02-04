package hotel.reservation_service.service;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.dto.ReservationDatesRequestDto;

public interface ReservationService {

    APIResponseDto saveReservation(String userEmail, Long roomId, String token, ReservationDatesRequestDto reservationDates);

    APIResponseDto addServiceToReservationByDate(String userEmail, Long serviceId,ReservationDatesRequestDto reservationDates, String token);

    APIResponseDto getReservation(Long userId, String token);
}
