package hotel.user_service.exception;

import hotel.user_service.exception.exceptions.LoginFailedException;
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

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> loginFailed(LoginFailedException ex, WebRequest request){

        // Log the exception with full stack trace
        log.error("LoginFailedException occurred for request {}: {}",  request.getDescription(false), ex.getMessage(), ex);

        // Build structured error response object to return as JSON to the client
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Unauthorized")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }


}
