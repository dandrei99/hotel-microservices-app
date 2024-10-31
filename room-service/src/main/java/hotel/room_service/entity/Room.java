package hotel.room_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Enumerated(EnumType.STRING) // Ensure enum is stored as a string in the database
    private RoomType roomType;

    private int maxOccupancy;

    private String description;

    private double pricePerNight;

    private boolean availability = true;

    private double surface;

    public enum RoomType {
        SINGLE,
        DOUBLE,
        SUITE
    }
}
