package hotel.reservation_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class ReservationDatesRequestDto {
    @NotNull(message = "Check-in date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;
}
