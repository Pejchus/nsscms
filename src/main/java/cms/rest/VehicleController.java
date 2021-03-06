package cms.rest;

import cms.model.Vehicle;
import cms.service.RegularuserService;
import cms.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class VehicleController {


    private final VehicleService vehicleService;
    private final RegularuserService regularuserService;

    @Autowired
    public VehicleController(VehicleService vehicleService, RegularuserService regularuserService) {
        this.vehicleService = vehicleService;

        this.regularuserService = regularuserService;
    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public ResponseEntity<String> getvehicles() {
        List<Vehicle> vehicles = vehicleService.findall();
        String result = "";
        for (Vehicle v : vehicles) {
            result = result + v.getLicenseplate() + "@";
        }
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/available", method = RequestMethod.GET)
    public ResponseEntity<String> getFreeVehicles() {
        List<Vehicle> vehicles = vehicleService.findallavailable();
        String result = "";
        for (Vehicle v : vehicles) {
            result = result + v.getLicenseplate() + "@";
        }
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/create", method = RequestMethod.GET)
    public ResponseEntity<String> createVehicle(@RequestParam String licence, @RequestParam String driver) {
        try {
            if (driver.equals("null")) {
                vehicleService.createVehicle(licence, null);
            } else {
                vehicleService.createVehicle(licence, driver);
                regularuserService.assignVehicle(driver, licence);
            }
            return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/vehicle/modify", method = RequestMethod.GET)
    public ResponseEntity<String> modifyVehicle(@RequestParam String vehicle, @RequestParam String driver) {
        if (driver.equals("null")) {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
        try {
            regularuserService.assignVehicle(vehicleService.find(vehicle).getDriver(), null);
        } catch (Exception e) {
        }
        try {
            vehicleService.assignVehicle(vehicle, driver);
            regularuserService.assignVehicle(driver, vehicle);
            return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/vehicle/unassigne", method = RequestMethod.GET)
    public ResponseEntity<String> unassigneVehicle(@RequestParam String vehicle) {
        try {
            try {
                regularuserService.assignVehicle(vehicleService.find(vehicle).getDriver(), null);
            } catch (Exception e) {
            }
            vehicleService.assignVehicle(vehicle, null);
            return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/vehicle/remove", method = RequestMethod.GET)
    public ResponseEntity<String> deleteVehicle(@RequestParam String vehicle) {
        try {
            try {
                regularuserService.assignVehicle(vehicleService.find(vehicle).getDriver(), null);
            } catch (Exception e) {
            }
            vehicleService.destroyVehicle(vehicle);
            return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/vehicle-driver", method = RequestMethod.GET)
    public ResponseEntity<String> getVehiclesWithDriver() {
        List<Vehicle> vehicles = vehicleService.findall();
        String result = "";
        for (Vehicle v : vehicles) {
            result = result + v.getLicenseplate() + "@" + v.getDriver() + "@";
        }
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

