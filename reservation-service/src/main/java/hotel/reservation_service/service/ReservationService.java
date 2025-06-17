package hotel.reservation_service.service;

import hotel.reservation_service.dto.APIResponseDto;
import hotel.reservation_service.dto.ReservationDatesRequestDto;

public interface ReservationService {

    APIResponseDto saveReservation(String userEmail, Long roomId, String token, ReservationDatesRequestDto reservationDates);

    APIResponseDto addServiceToReservationByDate(String userEmail, Long serviceId, String token);

    APIResponseDto getReservation(String userEmail, String token);

    APIResponseDto removeServiceFromReservation(String userEmail, Long serviceId, String token);
}
