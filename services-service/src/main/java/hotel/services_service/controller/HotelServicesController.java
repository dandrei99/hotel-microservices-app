package hotel.services_service.controller;

import hotel.services_service.dto.HotelServicesDto;
import hotel.services_service.service.HotelServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/services")
public class HotelServicesController {

    @Autowired
    HotelServicesService hotelServicesService;

    @PostMapping
    public ResponseEntity<HotelServicesDto> saveServices(@RequestBody HotelServicesDto hotelServicesDto){
        HotelServicesDto savedHotelServicesDto = hotelServicesService.saveServices(hotelServicesDto);
        return new ResponseEntity<>(savedHotelServicesDto, HttpStatus.CREATED);
    }
}
