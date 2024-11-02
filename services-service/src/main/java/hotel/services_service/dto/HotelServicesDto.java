package hotel.services_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelServicesDto {


    private Long serviceId;

    private String serviceName;

    private String description;

    private double price;
}
