package hotel.reservation_service.exception.exceptions;

public class ServiceAlreadyAddedException extends RuntimeException {

        public ServiceAlreadyAddedException(Long serviceId){
            super("Service with ID " + serviceId + " is already added to the reservation.");
        }
}
