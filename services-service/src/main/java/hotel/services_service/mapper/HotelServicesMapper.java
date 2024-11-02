package hotel.services_service.mapper;

import hotel.services_service.dto.HotelServicesDto;
import hotel.services_service.entity.HotelServices;

public class HotelServicesMapper {

    public static HotelServices mapToServices(HotelServicesDto hotelServicesDto){
        HotelServices hotelServices = new HotelServices();
        hotelServices.setServiceId(hotelServicesDto.getServiceId());
        hotelServices.setServiceName(hotelServicesDto.getServiceName());
        hotelServices.setDescription(hotelServicesDto.getDescription());
        hotelServices.setPrice(hotelServicesDto.getPrice());
        return hotelServices;
    }


    public static HotelServicesDto mapToServicesDto(HotelServices hotelServices){
        HotelServicesDto hotelServicesDto = new HotelServicesDto();
        hotelServicesDto.setServiceId(hotelServices.getServiceId());
        hotelServicesDto.setServiceName(hotelServices.getServiceName());
        hotelServicesDto.setDescription(hotelServices.getDescription());
        hotelServicesDto.setPrice(hotelServices.getPrice());
        return hotelServicesDto;
    }
}
