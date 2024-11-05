package hotel.reservation_service.service;

import hotel.reservation_service.dto.APIResponseDto;

public interface ReservationService {

    APIResponseDto saveReservation(Long userId, Long roomId);

    APIResponseDto addServiceToReservation(Long userId, Long serviceId);
}
