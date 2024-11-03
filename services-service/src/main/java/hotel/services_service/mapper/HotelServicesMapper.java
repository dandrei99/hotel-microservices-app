package hotel.services_service.mapper;

import hotel.services_service.dto.HotelServiceDto;
import hotel.services_service.entity.HotelService;

public class HotelServicesMapper {

    public static HotelService mapToServices(HotelServiceDto hotelServiceDto){
        HotelService hotelService = new HotelService();
        hotelService.setServiceId(hotelServiceDto.getServiceId());
        hotelService.setServiceName(hotelServiceDto.getServiceName());
        hotelService.setDescription(hotelServiceDto.getDescription());
        hotelService.setPrice(hotelServiceDto.getPrice());
        return hotelService;
    }


    public static HotelServiceDto mapToServicesDto(HotelService hotelService){
        HotelServiceDto hotelServiceDto = new HotelServiceDto();
        hotelServiceDto.setServiceId(hotelService.getServiceId());
        hotelServiceDto.setServiceName(hotelService.getServiceName());
        hotelServiceDto.setDescription(hotelService.getDescription());
        hotelServiceDto.setPrice(hotelService.getPrice());
        return hotelServiceDto;
    }
}
