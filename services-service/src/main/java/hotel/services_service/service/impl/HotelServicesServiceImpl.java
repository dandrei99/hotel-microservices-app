package hotel.services_service.service.impl;

import hotel.services_service.dto.HotelServiceDto;
import hotel.services_service.entity.HotelService;
import hotel.services_service.mapper.HotelServicesMapper;
import hotel.services_service.repository.HotelServicesRepository;
import hotel.services_service.service.HotelServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServicesServiceImpl implements HotelServicesService {

    @Autowired
    HotelServicesRepository hotelServicesRepository;

    @Override
    public HotelServiceDto saveServices(HotelServiceDto hotelServiceDto) {
        HotelService hotelService = HotelServicesMapper.mapToServices(hotelServiceDto);
        HotelService savedHotelService =  hotelServicesRepository.save(hotelService);
        HotelServiceDto savedHotelServiceDto = HotelServicesMapper.mapToServicesDto(savedHotelService);
        return savedHotelServiceDto;
    }
}
