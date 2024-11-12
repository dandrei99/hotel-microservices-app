package hotel.services_service.service;

import hotel.services_service.dto.HotelServiceDto;

import java.util.List;

public interface HotelServicesService {

    HotelServiceDto saveServices(HotelServiceDto hotelServiceDto);

    HotelServiceDto getServiceById(Long serviceId);

    List<HotelServiceDto> getAllServices();
}
