package hotel.reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long reservationId;
    private Long userId;  // ID of the user making the reservation
    private Long roomId;  // ID of the reserved room
    private List<Long> serviceIds;  // List of service IDs associated with the reservation
    private double totalPrice;
    private String reservationStatus;  // Status of the reservation (e.g., CONFIRMED, CANCELED)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ReservationDto{" +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", serviceIds=" + serviceIds +
                ", totalPrice=" + totalPrice +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
