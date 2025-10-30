package hotel.reservation_service.exception.exceptions;

public class NoReservationForUserException extends RuntimeException {

    public NoReservationForUserException(String email){
        super("User " + email + " doesn't have a reservation.");
    }
}
