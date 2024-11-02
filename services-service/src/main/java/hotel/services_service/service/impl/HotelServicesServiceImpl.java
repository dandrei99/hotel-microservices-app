package hotel.services_service.service.impl;

import hotel.services_service.dto.HotelServicesDto;
import hotel.services_service.entity.HotelServices;
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
    public HotelServicesDto saveServices(HotelServicesDto hotelServicesDto) {
        HotelServices hotelServices = HotelServicesMapper.mapToServices(hotelServicesDto);
        HotelServices savedHotelServices =  hotelServicesRepository.save(hotelServices);
        HotelServicesDto savedHotelServicesDto = HotelServicesMapper.mapToServicesDto(savedHotelServices);
        return savedHotelServicesDto;
    }
}
