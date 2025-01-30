package hotel.reservation_service.service;


import hotel.reservation_service.dto.HotelServiceDto;
import hotel.reservation_service.dto.ReservationDto;
import hotel.reservation_service.dto.RoomDto;
import hotel.reservation_service.entity.Reservation;
import hotel.reservation_service.repository.ReservationRepository;
import hotel.reservation_service.service.impl.ReservationServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class TestReservationServiceImpl {
    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationServiceImpl serviceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestRoomIsNOTAvailable(){
        Long roomId = 1L;
        LocalDate newCheckIn = LocalDate.of(2024,6,14);
        LocalDate newCheckOut = LocalDate.of(2024,6,18);
        List<Reservation> roomReservations = new ArrayList<>();

        Reservation r1 = new Reservation();
        r1.setRoomId(1L);
        r1.setCheckIn(LocalDate.of(2024,6,12));
        r1.setCheckOut(LocalDate.of(2024,6,13));
        r1.setReservationStatus(Reservation.ReservationStatus.CANCELED);
        roomReservations.add(r1);

        Reservation r2 = new Reservation();
        r2.setRoomId(1L);
        r2.setCheckIn(LocalDate.of(2024,6,19));
        r2.setCheckOut(LocalDate.of(2024,6,20));
        r2.setReservationStatus(Reservation.ReservationStatus.COMPLETED);
        roomReservations.add(r2);

        Reservation r3 = new Reservation();
        r3.setRoomId(1L);
        r3.setCheckIn(LocalDate.of(2024,6,5));
        r3.setCheckOut(LocalDate.of(2024,6,19));
        r3.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r3);

        Reservation r4 = new Reservation();
        r4.setRoomId(1L);
        r4.setCheckIn(LocalDate.of(2024,6,16));
        r4.setCheckOut(LocalDate.of(2024,6,20));
        r4.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r4);



        when(reservationRepository.findByRoomId(roomId)).thenReturn(roomReservations);

        boolean isRoomAvailable = serviceImpl.isRoomAvailable(1L, newCheckIn,newCheckOut);
        assertFalse(isRoomAvailable);
    }


    @Test
    public void TestRoomIsAvailable(){
        Long roomId = 1L;
        LocalDate newCheckIn = LocalDate.of(2024,6,14);
        LocalDate newCheckOut = LocalDate.of(2024,6,18);
        List<Reservation> roomReservations = new ArrayList<>();

        Reservation r1 = new Reservation();
        r1.setRoomId(1L);
        r1.setCheckIn(LocalDate.of(2024,6,12));
        r1.setCheckOut(LocalDate.of(2024,6,13));
        r1.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r1);

        Reservation r2 = new Reservation();
        r2.setRoomId(1L);
        r2.setCheckIn(LocalDate.of(2024,6,19));
        r2.setCheckOut(LocalDate.of(2024,6,20));
        r2.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r2);

        Reservation r3 = new Reservation();
        r3.setRoomId(1L);
        r3.setCheckIn(LocalDate.of(2024,6,5));
        r3.setCheckOut(LocalDate.of(2024,6,6));
        r3.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r3);

        Reservation r4 = new Reservation();
        r4.setRoomId(1L);
        r4.setCheckIn(LocalDate.of(2024,6,20));
        r4.setCheckOut(LocalDate.of(2024,6,24));
        r4.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r4);



        when(reservationRepository.findByRoomId(roomId)).thenReturn(roomReservations);

        boolean isRoomAvailable = serviceImpl.isRoomAvailable(1L, newCheckIn,newCheckOut);
        assertTrue(isRoomAvailable);
    }

    @Test
    public void TestNewReservationBetween2_OtherReservations(){
        Long roomId = 1L;
        LocalDate newCheckIn = LocalDate.of(2024,6,14);
        LocalDate newCheckOut = LocalDate.of(2024,6,18);
        List<Reservation> roomReservations = new ArrayList<>();

        Reservation r1 = new Reservation();
        r1.setRoomId(1L);
        r1.setCheckIn(LocalDate.of(2024,6,12));
        r1.setCheckOut(LocalDate.of(2024,6,14));
        r1.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r1);

        Reservation r2 = new Reservation();
        r2.setRoomId(1L);
        r2.setCheckIn(LocalDate.of(2024,6,18));
        r2.setCheckOut(LocalDate.of(2024,6,20));
        r2.setReservationStatus(Reservation.ReservationStatus.CONFIRMED);
        roomReservations.add(r2);

        when(reservationRepository.findByRoomId(roomId)).thenReturn(roomReservations);

        boolean isRoomAvailable = serviceImpl.isRoomAvailable(1L, newCheckIn,newCheckOut);
        assertTrue(isRoomAvailable);
    }


    @Test
    public void calculateTotalPriceOfReservation_NOServices(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCheckIn( LocalDate.of(2024,6,14));
        reservationDto.setCheckOut( LocalDate.of(2024,6,16));

        RoomDto roomDto = new RoomDto();
        roomDto.setPricePerNight(100);

        assertEquals(200,serviceImpl.calculateReservationPrice(roomDto.getPricePerNight(), reservationDto.getCheckIn(),reservationDto.getCheckOut(),null));
    }

    @Test
    public void calculateTotalPriceOfReservation_WithServices(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCheckIn( LocalDate.of(2024,6,14));
        reservationDto.setCheckOut( LocalDate.of(2024,6,16));

        RoomDto roomDto = new RoomDto();
        roomDto.setPricePerNight(100);

        List<HotelServiceDto> userHotelServices = new ArrayList<>();
        HotelServiceDto hotelService1 = new HotelServiceDto();
        hotelService1.setPrice(45);
        userHotelServices.add(hotelService1);

        HotelServiceDto hotelService2 = new HotelServiceDto();
        hotelService2.setPrice(15);
        userHotelServices.add(hotelService2);

        HotelServiceDto hotelService3 = new HotelServiceDto();
        hotelService3.setPrice(20);
        userHotelServices.add(hotelService3);

        HotelServiceDto hotelService4 = new HotelServiceDto();
        hotelService4.setPrice(50);
        userHotelServices.add(hotelService4);

        assertEquals(330,serviceImpl.calculateReservationPrice(roomDto.getPricePerNight(), reservationDto.getCheckIn(),reservationDto.getCheckOut(), userHotelServices));
    }



}
