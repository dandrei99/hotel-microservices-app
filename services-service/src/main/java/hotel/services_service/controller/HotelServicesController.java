package hotel.services_service.controller;

import hotel.services_service.dto.HotelServiceDto;
import hotel.services_service.service.HotelServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/services")
public class HotelServicesController {

    @Autowired
    HotelServicesService hotelServicesService;

    @PostMapping
    public ResponseEntity<HotelServiceDto> saveService(@RequestBody HotelServiceDto hotelServiceDto){
        HotelServiceDto savedHotelServiceDto = hotelServicesService.saveServices(hotelServiceDto);
        return new ResponseEntity<>(savedHotelServiceDto, HttpStatus.CREATED);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<HotelServiceDto> getService(@PathVariable("serviceId") Long serviceId){
        HotelServiceDto hotelServiceDto = hotelServicesService.getServiceById(serviceId);
        return new ResponseEntity<>(hotelServiceDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelServiceDto>> getAllServices() {
        List<HotelServiceDto> hotelServicesDto = hotelServicesService.getAllServices();
        return new ResponseEntity<>(hotelServicesDto, HttpStatus.OK);
    }
}
