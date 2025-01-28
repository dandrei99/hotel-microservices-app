package hotel.reservation_service.service;


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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
