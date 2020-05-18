package cms.rest;

import cms.model.Shipment;
import cms.service.Coder;
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
         shipmentService.createShipment(cargo,destination,vehicle,date);
    }

    @RequestMapping(value = "/activeshipment", method = RequestMethod.GET)
    public ResponseEntity<String> activeShipment(){
        return new ResponseEntity<>(Coder.codeShipment(shipmentService.findactive("")), HttpStatus.OK);
    }
    @RequestMapping(value = "/activeshipment/driver", method = RequestMethod.GET)
    public ResponseEntity<String> activeShipment(@RequestParam String driver){
        return new ResponseEntity<>(Coder.codeShipment(shipmentService.findactive(driver)), HttpStatus.OK);
    }

    @RequestMapping(value = "/shipment", method = RequestMethod.GET)
    public ResponseEntity<String> shipment(){
        return new ResponseEntity<>(Coder.codeShipment(shipmentService.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/inactiveShipment", method = RequestMethod.GET)
    public ResponseEntity<String> shipmentInactive(){
        return new ResponseEntity<>(Coder.codeShipment(shipmentService.findfinish("")), HttpStatus.OK);
    }
    @RequestMapping(value = "/inactiveShipment/driver", method = RequestMethod.GET)
    public ResponseEntity<String> shipmentInactive(@RequestParam String driver){
        return new ResponseEntity<>(Coder.codeShipment(shipmentService.findfinish(driver)), HttpStatus.OK);
    }
    @RequestMapping(value = "/finished", method = RequestMethod.GET)
    public ResponseEntity<String> shipmentFinished(@RequestParam String driver){
        shipmentService.setfinish(driver);
        System.out.println("kokot");
        return new ResponseEntity<>("true", HttpStatus.OK);
    }





    }




