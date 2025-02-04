package hotel.reservation_service.service.impl;

import hotel.reservation_service.dto.*;
import hotel.reservation_service.entity.Reservation;
import hotel.reservation_service.mapper.ReservationMapper;
import hotel.reservation_service.repository.ReservationRepository;
import hotel.reservation_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public APIResponseDto saveReservation(String userEmail, Long roomId, String token, ReservationDatesRequestDto reservationDates) {
        log.info("Starting to create reservation for user: {} and roomId: {}", userEmail, roomId);

        WebClient webClient = webClientBuilder.build();

        if(!isRoomAvailable(roomId,reservationDates.getCheckIn(),reservationDates.getCheckOut())){
            throw new RuntimeException("Room is not available in the selected time period");
        }

        UserDto userDto = getUserByEmailResponseApi(userEmail, token);
        log.info("Fetched UserDto: {}", userDto);
        RoomDto roomDto = getRoomResponseApi(roomId, token);
        log.info("Fetched RoomDto: {}",    roomDto);

        // Create and save a new Reservation
        Reservation reservation = new Reservation();
        reservation.setUserId(userDto.getUserId());
        reservation.setRoomId(roomId);
        reservation.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        reservation.setCheckIn(reservationDates.getCheckIn());
        reservation.setCheckOut(reservationDates.getCheckOut());
        reservation.setTotalPrice(calculateReservationPrice(roomDto.getPricePerNight(),reservationDates.getCheckIn(),reservationDates.getCheckOut(), null));

        Reservation savedReservation = reservationRepository.save(reservation);

        // Map to ReservationDto
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(savedReservation.getReservationId());
        reservationDto.setUserId(savedReservation.getUserId());
        reservationDto.setRoomId(savedReservation.getRoomId());
        reservationDto.setReservationStatus(String.valueOf(savedReservation.getReservationStatus()));
        reservationDto.setCreatedAt(savedReservation.getCreatedAt());
        reservationDto.setCheckIn(savedReservation.getCheckIn());
        reservationDto.setCheckOut(savedReservation.getCheckOut());
        reservationDto.setTotalPrice(savedReservation.getTotalPrice());
        log.info("User {} has the following reservation: {}", userEmail, reservationDto);


        //create the API Response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setReservation(reservationDto);

        log.info("Completed reservation creation for user: {} and roomId: {}", userEmail, roomId);
        return apiResponseDto;
    }

    @Override
    public APIResponseDto addServiceToReservationByDate(String userEmail, Long serviceId, ReservationDatesRequestDto reservationDates, String token) {
        log.info("Adding serviceId: {} to reservation for user: {}", serviceId, userEmail);

        UserDto userDto = getUserByEmailResponseApi(userEmail, token);
        log.info("Fetched UserDto: {}", userDto);

        //CHECK RESERVATION EXISTS FOR USER BETWEEN THOSE DATES
        Reservation reservation = reservationRepository.findByUserIdAndCheckInAndCheckOut(userDto.getUserId(), reservationDates.getCheckIn(), reservationDates.getCheckOut())
                .orElseThrow(() -> {
                    log.info("No reservation found for user: {}, between those dates:{} - {}", userEmail, reservationDates.getCheckIn(), reservationDates.getCheckOut());
                    return new RuntimeException("No reservation found for this user, between those dates");
                });

        //CHECK SERVICE DOESN'T EXIST ALREADY IN RESERVATION
        List<Long> reservationServices = reservation.getServiceIds();
        if (reservationServices.contains(serviceId)) {
            log.info("Service with ID: {} is already added to the reservation for user: {}", serviceId, userEmail);
            throw new IllegalArgumentException("Service with ID " + serviceId + " is already added to the reservation.");
        }
        //ADD SERVICEID TO RESERVATION
        reservationServices.add(serviceId);

        //POPULATE userHotelServices TO HAVE ALL DATA ABOUT EACH SERVICE
        List<HotelServiceDto> userHotelServices = new ArrayList<>();

        for (int i = 0; i < reservationServices.size(); i++) {

            HotelServiceDto serviceDto = getHotelServiceResponseApi(reservationServices.get(i), token);
            log.info("Fetched HotelServiceDto: {}", serviceDto);
            userHotelServices.add(serviceDto);
        }

        //GET RoomDto, TO HAVE DATA ABOUT THE ROOM, NEEDED FOR PRICE CALCULATION
        RoomDto roomDto = getRoomResponseApi(reservation.getRoomId(), token);
        log.info("Fetched RoomDto: {}", roomDto);

        //UPDATE RESERVATION WITH NEW SERVICE AND WITH NEW TOTAL PRICE OF RESERVATION
        reservation.setServiceIds(reservationServices);
        reservation.setTotalPrice(calculateReservationPrice(roomDto.getPricePerNight(), reservationDates.getCheckIn(), reservationDates.getCheckOut(), userHotelServices));
        Reservation updatedReservation = reservationRepository.save(reservation);

        ReservationDto reservationDto = ReservationMapper.mapToReservationDto(updatedReservation);

        //create the API Response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setReservation(reservationDto);
        apiResponseDto.setUser(userDto);
        apiResponseDto.setRoom(roomDto);
        apiResponseDto.setHotelServices(userHotelServices);

        log.info("Completed adding serviceId: {} to reservation for user: {}", serviceId, userEmail);
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

    public boolean isRoomAvailable(Long roomId,  LocalDate newCheckIn, LocalDate newCheckOut){
        List<Reservation> RoomReservations = reservationRepository.findByRoomId(roomId);

        for(Reservation reservation: RoomReservations){
            //rule 0: keep only CONFIRMED reservations
            if(!reservation.getReservationStatus().equals(Reservation.ReservationStatus.CONFIRMED)){
//                RoomReservations.remove(reservation);
                continue;
            }
            //rule 1: newCheckIn date matches checkIn date of another reservation -> Room not available
            if(reservation.getCheckIn().equals(newCheckIn)){
                return false;
            }
            //rule 2: newCheckOut date matches checkOut date of another reservation -> Room not available
            if(reservation.getCheckOut().equals(newCheckOut)){
                return false;
            }
            //rule 3: Condition for a new reservation to be ok:
            // (newCheckOut date <= checkIn date) || (newCheckIn date >= checkOut date)
            if(!((newCheckIn.isAfter(reservation.getCheckOut()) || newCheckIn.isEqual(reservation.getCheckOut()))
                    || (newCheckOut.isBefore(reservation.getCheckIn()) || newCheckOut.isEqual(reservation.getCheckIn()))  )){
                //new reservation is overlapping with an existing reservation
//                log.info("new reservation with dates: {} is overlapping with reservation: {}, with ",reservationDates, reservation);
                return false;
            }
        }
        return true;
    }

    public double calculateReservationPrice(double pricePerNight, LocalDate checkIn, LocalDate checkOut, List<HotelServiceDto> userHotelServices) {
        int nrOfDays = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = nrOfDays * pricePerNight;
        if (userHotelServices == null) {
            return totalPrice;
        } else {
            for (HotelServiceDto hotelService : userHotelServices) {
                totalPrice += hotelService.getPrice();
            }
            return totalPrice;
        }
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

    private UserDto getUserByEmailResponseApi(String userEmail, String token) {
        log.info("Fetching UserDto for userEmail: {}", userEmail);

//        WebClient webClient = webClientBuilder.build();
        WebClient securedWebClient = webClientBuilder.build();
        UserDto userDto = securedWebClient.get()
                .uri("http://localhost:9191/api/users/getUserByEmail/" + userEmail)
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
