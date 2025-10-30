package hotel.reservation_service.exception.exceptions;

public class UserAlreadyHasReservationException extends RuntimeException{

    public UserAlreadyHasReservationException(String userEmail, Long roomId){
        super("User " + userEmail + " already has a reservation on room Nr." + roomId);
    }
}
