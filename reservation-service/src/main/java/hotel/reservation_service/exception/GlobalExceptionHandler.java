package hotel.reservation_service.exception;


import hotel.reservation_service.exception.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceAlreadyAddedException.class)
    public ResponseEntity<ErrorResponse> serviceAlreadyAddedToReservation(ServiceAlreadyAddedException ex, WebRequest request){

        // Log the exception with full stack trace
        log.error("ServiceAlreadyAddedException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        // Build structured error response object to return as JSON to the client
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoReservationForUserException.class)
    public ResponseEntity<ErrorResponse> noReservationFoundForUser(NoReservationForUserException ex, WebRequest request){

        log.error("NoReservationForUserException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponse> roomNotAvailable(RoomNotAvailableException ex, WebRequest request){

        log.error("RoomNotAvailableException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyHasReservationException.class)
    public ResponseEntity<ErrorResponse> userAlreadyHasReservation(UserAlreadyHasReservationException ex, WebRequest request){

        log.error("UserAlreadyHasReservationException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> serviceNotAvailable (ServiceUnavailableException ex, WebRequest request){

        log.error("ServiceUnavailableException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Service Unavailable")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }
}
