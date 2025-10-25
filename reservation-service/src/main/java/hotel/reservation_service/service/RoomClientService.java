package hotel.reservation_service.service;

import hotel.reservation_service.dto.RoomDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RoomClientService {
    private final WebClient.Builder webClientBuilder;
    private static final Logger log = LoggerFactory.getLogger(RoomClientService.class);

    public RoomClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Value("${room.service.base-url}")
    private String roomServiceBaseUrl;

    @CircuitBreaker(name = "roomService", fallbackMethod = "fallbackRoom")
    public RoomDto getRoom(Long roomId, String token) {
        WebClient securedWebClient = webClientBuilder.build();
        RoomDto roomDto =  securedWebClient.get()
                .uri(roomServiceBaseUrl + roomId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(RoomDto.class)
                .block();
        return roomDto;
    }


    public RoomDto fallbackRoom(Long roomId, String token, Throwable throwable) {
        log.error("Fallback triggered for RoomService. Reason: {}", throwable.getMessage());
        RoomDto fallbackDto = new RoomDto();
        fallbackDto.setRoomId(roomId);
        fallbackDto.setRoomType("Unavailable");
        fallbackDto.setDescription("Room info not available");
        return fallbackDto;
    }
}
