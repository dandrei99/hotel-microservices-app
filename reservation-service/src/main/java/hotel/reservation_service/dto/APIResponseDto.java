package hotel.reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {

    private UserDto user;
    private  RoomDto room;
    private List<HotelServiceDto> hotelServices;
    private ReservationDto reservation;

}
