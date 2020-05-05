package cms.rest;

import cms.model.Regularuser;
import cms.model.Shipment;
import cms.model.Vehicle;
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







    }

