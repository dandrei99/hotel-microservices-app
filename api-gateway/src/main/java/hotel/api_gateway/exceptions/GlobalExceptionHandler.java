package hotel.api_gateway.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Configuration
@Order(-2)
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if(ex instanceof ResponseStatusException responseEx){
            status = responseEx.getStatusCode();
            message = responseEx.getReason();
        }

        String reason = (status instanceof HttpStatus httpStatus)
                ? httpStatus.getReasonPhrase()
                : "Error";

        String jsonResponse = String.format("""
            {
                "timestamp": "%s",
                "status": %d,
                "error": "%s",
                "message": "%s",
                "path": "%s"
            }
            """,
                LocalDateTime.now(),
                status.value(),
                reason ,
                message != null ? message : "Unexpected error",
                exchange.getRequest().getURI().getPath()
        );

        log.error("Exception occured on {}, response {}",exchange.getRequest().getPath(), jsonResponse);

        byte[] bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        exchange.getResponse().setStatusCode(status);

        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(bytes)));
    }
}
