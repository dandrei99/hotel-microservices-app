package hotel.reservation_service.mapper;

import hotel.reservation_service.dto.ReservationDto;
import hotel.reservation_service.entity.Reservation;

public class ReservationMapper {

    public static ReservationDto mapToReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto(
                reservation.getReservationId(),
                reservation.getUserId(),
                reservation.getRoomId(),
                reservation.getServiceIds(),
                reservation.getTotalPrice(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                String.valueOf(reservation.getReservationStatus()),
                reservation.getCreatedAt()
        );
        return reservationDto;
    }

    public static Reservation mapToReservation(ReservationDto reservationDto){
        Reservation reservation = new Reservation(
                reservationDto.getReservationId(),
                reservationDto.getUserId(),
                reservationDto.getRoomId(),
                reservationDto.getServiceIds(),
                reservationDto.getTotalPrice(),
                reservationDto.getCheckIn(),
                reservationDto.getCheckOut(),
                Reservation.ReservationStatus.valueOf(reservationDto.getReservationStatus()),
                reservationDto.getCreatedAt()
        );
        return reservation;
    }
}
