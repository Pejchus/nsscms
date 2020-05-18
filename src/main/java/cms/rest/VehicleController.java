package cms.rest;

import cms.model.Regularuser;
import cms.model.Shipment;
import cms.model.Vehicle;
import cms.service.RegularuserService;
import cms.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.jaxws.SimpleHttpServerJaxWsServiceExporter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("")
public class VehicleController {


        private final VehicleService vehicleService;


        @Autowired
        public VehicleController(VehicleService vehicleService) {
            this.vehicleService = vehicleService;

        }
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public ResponseEntity<String> getvehicles(){
            List<Vehicle> vehicles = vehicleService.findall();
            String result ="";
            for (Vehicle v:vehicles){
                result = result +v.getLicenseplate()+"@";
            }
            System.out.println(result);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/vehicles/available", method = RequestMethod.GET)
    public ResponseEntity<String> getFreeVehicles(){
        List<Vehicle> vehicles = vehicleService.findallavailable();
        String result ="";
        for (Vehicle v:vehicles){
            result = result +v.getLicenseplate()+"@";
        }
        System.out.println(result);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/vehicles/create", method = RequestMethod.GET)
    public ResponseEntity<String> createVehicle(@RequestParam String licence,@RequestParam String driver){
        try {
            vehicleService.createVehicle(licence,driver);
            return new ResponseEntity<>("true",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("false",HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/vehicle-driver", method = RequestMethod.GET)
    public ResponseEntity<String> getVehiclesWithDriver(){
        List<Vehicle> vehicles = vehicleService.findall();
        String result ="";
        for (Vehicle v:vehicles){
            result = result +v.getLicenseplate()+"@"+v.getDriver()+"@";
        }
        System.out.println(result);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }







    }

