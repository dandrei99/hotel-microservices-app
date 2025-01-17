package hotel.reservation_service.service.impl;

import hotel.reservation_service.dto.*;
import hotel.reservation_service.entity.Reservation;
import hotel.reservation_service.mapper.ReservationMapper;
import hotel.reservation_service.repository.ReservationRepository;
import hotel.reservation_service.service.ReservationService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    private final WebClient.Builder webClientBuilder;

    @Qualifier("unsecuredWebClientBuilder")
    private final WebClient.Builder unsecuredWebClientBuilder;

//    private WebClient webClient;
//
//    @PostConstruct
//    public void initWebClient() {
//        this.webClient = webClientBuilder.build();
//        log.info("Initialized WebClient: {}", webClient);
//    }

    @Override
    public APIResponseDto saveReservation(Long userId, Long roomId, String token) {
        log.info("Starting to create reservation for userId: {} and roomId: {}", userId, roomId);

        WebClient webClient = webClientBuilder.build();

        // check if user has a reservation
        Optional<Reservation> hasReservation = reservationRepository.findByUserId(userId);
        if(hasReservation.isPresent()){
            log.info("User with ID: {} already has a reservation", userId);
            throw new RuntimeException("User already has a reservation");
        }

        // Create and save a new Reservation
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(reservation);

        // Map to ReservationDto
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(savedReservation.getReservationId());
        reservationDto.setUserId(savedReservation.getUserId());
        reservationDto.setRoomId(savedReservation.getRoomId());
        reservationDto.setReservationStatus(String.valueOf(savedReservation.getReservationStatus()));
        reservationDto.setCreatedAt(savedReservation.getCreatedAt());
        log.info("User with Id: {} has the following reservation: {}", userId, reservationDto);

        UserDto userDto = getUserResponseApi(userId, token);
        log.info("Fetched UserDto: {}", userDto);
        RoomDto roomDto = getRoomResponseApi(roomId, token);
        log.info("Fetched RoomDto: {}", roomDto);

        //create the API Response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setReservation(reservationDto);

        log.info("Completed reservation creation for userId: {} and roomId: {}", userId, roomId);
        return apiResponseDto;
    }

    @Override
    public APIResponseDto addServiceToReservation(Long userId, Long serviceId, String token) {
        log.info("Adding serviceId: {} to reservation for userId: {}", serviceId, userId);

        Reservation reservation = reservationRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.info("No reservation found for userId: {}", userId);
                    return new RuntimeException("No reservation found for this user");
                });

        //add service
        List<Long> reservationServices = reservation.getServiceIds();
        if (reservationServices.contains(serviceId)) {
            log.info("Service with ID: {} is already added to the reservation for userId: {}", serviceId, userId);
            throw new IllegalArgumentException("Service with ID " + serviceId + " is already added to the reservation.");
        }
        reservationServices.add(serviceId);
        reservation.setServiceIds(reservationServices);

        Reservation updatedReservation = reservationRepository.save(reservation);
        ReservationDto reservationDto = ReservationMapper.mapToReservationDto(updatedReservation);

        UserDto userDto = getUserResponseApi(userId, token);
        log.info("Fetched UserDto: {}", userDto);

        //set existing hotel services of the reservation to the APIResponseDto
        List<HotelServiceDto> userHotelServices = new ArrayList<>();

        for (int i = 0; i < reservationServices.size(); i++) {

            HotelServiceDto serviceDto = getHotelServiceResponseApi(reservationServices.get(i), token);
            log.info("Fetched HotelServiceDto: {}", serviceDto);
            userHotelServices.add(serviceDto);
        }

        RoomDto roomDto = getRoomResponseApi(reservationDto.getRoomId(), token);
        log.info("Fetched RoomDto: {}", roomDto);

        //create the API Response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setReservation(reservationDto);
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setHotelServices(userHotelServices);

        log.info("Completed adding serviceId: {} to reservation for userId: {}", serviceId, userId);
        return apiResponseDto;

    }

    @Override
    public APIResponseDto getReservation(Long userId, String token) {
        log.info("Fetching reservation for userId: {}", userId);

        //ReservationDto
        Reservation reservation = reservationRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.info("No reservation found for userId: {}", userId);
                    return new RuntimeException("No reservation found for this user");
                });
        ReservationDto reservationDto = ReservationMapper.mapToReservationDto(reservation);
        log.info("User with Id: {} has the following reservation: {}", userId, reservationDto);

        //RoomDto
        RoomDto roomDto = getRoomResponseApi(reservationDto.getRoomId(), token);
        log.info("Fetched RoomDto: {}", roomDto);

        //UserDto
        UserDto userDto = getUserResponseApi(userId, token);
        log.info("Fetched UserDto: {}", userDto);

        //HotelServiceDto
        List<HotelServiceDto> userHotelServices = new ArrayList<>();
        List<Long> reservationServices = reservation.getServiceIds();
        for (int i = 0; i < reservationServices.size(); i++) {

            HotelServiceDto serviceDto = getHotelServiceResponseApi(reservationServices.get(i), token);
            log.info("Fetched HotelServiceDto: {}", serviceDto);
            userHotelServices.add(serviceDto);
        }

        //create the API Response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setReservation(reservationDto);
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setHotelServices(userHotelServices);

        log.info("Completed getting reservation details for userId: {}", userId);
        return apiResponseDto;
    }

    private UserDto getUserResponseApi(Long userId, String token) {
        log.info("Fetching UserDto for userId: {}", userId);

//        WebClient webClient = webClientBuilder.build();
        WebClient securedWebClient = webClientBuilder.build();
        UserDto userDto = securedWebClient.get()
                .uri("http://localhost:9191/api/users/" + userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // Pass token
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        log.info("Fetched UserDto: {}", userDto);
        return userDto;
    }

    private RoomDto getRoomResponseApi(Long roomId, String token) {
        log.info("Fetching RoomDto for roomId: {}", roomId);

//        WebClient unsecuredWebClient = unsecuredWebClientBuilder.build();
        WebClient securedWebClient = webClientBuilder.build();
        RoomDto roomDto = securedWebClient.get()
                .uri("http://localhost:9191/api/rooms/" + roomId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // Pass token
                .retrieve()
                .bodyToMono(RoomDto.class)
                .block();

        return roomDto;
    }

    private HotelServiceDto getHotelServiceResponseApi(Long serviceId, String token) {

//        WebClient unsecuredWebClient = unsecuredWebClientBuilder.build();
        WebClient securedWebClient = webClientBuilder.build();
        HotelServiceDto serviceDto = securedWebClient.get()
                .uri("http://localhost:9191/api/services/" + serviceId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // Pass token
                .retrieve()
                .bodyToMono(HotelServiceDto.class)
                .block();

        return serviceDto;
    }

}
