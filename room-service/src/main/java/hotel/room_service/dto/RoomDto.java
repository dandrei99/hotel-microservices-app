package hotel.room_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long roomId;
    private String roomType; // Representing RoomType as a String
    private int maxOccupancy;
    private String description;
    private double pricePerNight;
    private boolean availability;
    private double surface;
}
