package hotel.reservation_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private Long userId;  // Reference to the user making the reservation

    @Column(nullable = false)
    private Long roomId;  // Reference to the reserved room

    @ElementCollection
    @CollectionTable(name = "reservation_services_mapping", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "service_id")
    private List<Long> serviceIds;  // List of service IDs associated with the reservation

    private double totalPrice;

    @Column(nullable = true)  //(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = true)  //(nullable = false)
    private LocalDate checkOut;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public enum ReservationStatus {
        CONFIRMED,
        CANCELED,
        COMPLETED
    }
}
