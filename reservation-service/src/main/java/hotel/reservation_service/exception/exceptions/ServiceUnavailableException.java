package hotel.reservation_service.exception.exceptions;

public class ServiceUnavailableException extends RuntimeException{

    public ServiceUnavailableException(String service){
        super(service + " not available");
    }
}
