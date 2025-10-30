package hotel.reservation_service.exception.exceptions;

import java.time.LocalDate;

public class RoomNotAvailableException extends RuntimeException{

    public RoomNotAvailableException(Long roomId, LocalDate checkIn, LocalDate checkOut){
        super("Room Nr. "+ roomId + " not available between " + checkIn + " and " + checkOut);
    }
}
