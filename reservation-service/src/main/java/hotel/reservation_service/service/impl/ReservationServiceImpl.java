package hotel.reservation_service.service.impl;

import hotel.reservation_service.dto.*;
import hotel.reservation_service.entity.Reservation;
import hotel.reservation_service.mapper.ReservationMapper;
import hotel.reservation_service.repository.ReservationRepository;
import hotel.reservation_service.service.ReservationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    WebClient webClient;

    @Override
    public APIResponseDto saveReservation(Long userId, Long roomId) {
        log.info("Starting to create reservation for userId: {} and roomId: {}", userId, roomId);

        // check user doesn't already have a reservation
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

        UserDto userDto = getUserResponseApi(userId);
        log.info("Fetched UserDto: {}", userDto);
        RoomDto roomDto = getRoomResponseApi(roomId);
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
    public APIResponseDto addServiceToReservation(Long userId, Long serviceId) {
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

        UserDto userDto = getUserResponseApi(userId);
        log.info("Fetched UserDto: {}", userDto);

        //set existing hotel services of the reservation to the APIResponseDto
        List<HotelServiceDto> userHotelServices = new ArrayList<>();

        for (int i = 0; i < reservationServices.size(); i++) {

            HotelServiceDto serviceDto = getHotelServiceResponseApi(reservationServices.get(i));
            log.info("Fetched HotelServiceDto: {}", serviceDto);
            userHotelServices.add(serviceDto);
        }

        RoomDto roomDto = getRoomResponseApi(reservationDto.getRoomId());
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
    public APIResponseDto getReservation(Long userId) {
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
        RoomDto roomDto = getRoomResponseApi(reservationDto.getRoomId());
        log.info("Fetched RoomDto: {}", roomDto);

        //UserDto
        UserDto userDto = getUserResponseApi(userId);
        log.info("Fetched UserDto: {}", userDto);

        //HotelServiceDto
        List<HotelServiceDto> userHotelServices = new ArrayList<>();
        List<Long> reservationServices = reservation.getServiceIds();
        for (int i = 0; i < reservationServices.size(); i++) {

            HotelServiceDto serviceDto = getHotelServiceResponseApi(reservationServices.get(i));
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

    private UserDto getUserResponseApi(Long userId) {

        UserDto userDto = webClient.get()
                .uri("http://localhost:8080/api/users/" + userId)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        return userDto;
    }

    private RoomDto getRoomResponseApi(Long roomId) {

        RoomDto roomDto = webClient.get()
                .uri("http://localhost:8081/api/rooms/" + roomId)
                .retrieve()
                .bodyToMono(RoomDto.class)
                .block();

        return roomDto;
    }

    private HotelServiceDto getHotelServiceResponseApi(Long serviceId) {

        HotelServiceDto serviceDto = webClient.get()
                .uri("http://localhost:8082/api/services/" + serviceId)
                .retrieve()
                .bodyToMono(HotelServiceDto.class)
                .block();

        return serviceDto;
    }

}
