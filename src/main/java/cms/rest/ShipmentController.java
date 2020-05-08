package cms.rest;

import cms.model.Shipment;
import cms.service.ShipmentService;
import cms.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final VehicleService vehicleService;


    @Autowired
    public ShipmentController(ShipmentService shipmentService, VehicleService vehicleService) {
        this.shipmentService = shipmentService;

        this.vehicleService = vehicleService;
    }
    @RequestMapping(value = "/createshipment", method = RequestMethod.GET)
    public void postShipment(@RequestParam String cargo,@RequestParam String date,@RequestParam String vehicle, @RequestParam String destination){
        System.out.println(cargo);
         shipmentService.createShipment(cargo,destination,vehicle);
    }

    @RequestMapping(value = "/activeshipment", method = RequestMethod.GET)
    public ResponseEntity<String> activeShipment(){
        return new ResponseEntity<>("jebu@ti na to@zlomim si s tim mozek@ty datumy vyprcany@udelej si@to sam do pici@", HttpStatus.OK);
    }

    @RequestMapping(value = "/shipment", method = RequestMethod.GET)
    public ResponseEntity<String> shipment(){
        return new ResponseEntity<>("jebu@ti na to@zlomim si s tim mozek@ty datumy vyprcany@udelej si@to sam do pici@", HttpStatus.OK);
    }

    @RequestMapping(value = "/inactiveShipment", method = RequestMethod.GET)
    public ResponseEntity<String> Shipment(){
        return new ResponseEntity<>("jebu@ti na to@zlomim si s tim mozek@ty datumy vyprcany@udelej si@to sam do pici@", HttpStatus.OK);
    }





    }




