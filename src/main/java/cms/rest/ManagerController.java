package cms.rest;

import cms.model.Regularuser;
import cms.model.Shipment;
import cms.model.Systemmanager;
import cms.model.Vehicle;
import cms.service.Coder;
import cms.service.RegularuserService;
import cms.service.SystemmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
public class ManagerController {
    private final SystemmanagerService service;
    private final RegularuserService regularuserService;


    @Autowired
    public ManagerController(SystemmanagerService service, RegularuserService regularuserService) {
        this.service = service;
        this.regularuserService = regularuserService;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Systemmanager find(@PathVariable String id) {
        return service.find(id);
    }



    @PutMapping(value = "/managers/shipments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean processShipment(@RequestBody Shipment shipment, Vehicle v){
        return service.processShipment(shipment.getId(),v.getLicenseplate());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll(){

        return new ResponseEntity<String>(Coder.codeManager(service.findAll()),HttpStatus.OK);
    }
    @GetMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@RequestParam String username){
        Systemmanager regularuser =  service.find(username);
        if(regularuser==null){
            return false;
        }
        service.delete(regularuser);
        return true;
    }
    @GetMapping(value = "/full",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAllFull(){
        return new ResponseEntity<String>((Coder.codeManagerFull(service.findAll())),HttpStatus.OK);
    }
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public boolean update(@RequestParam String previousUsername, @RequestParam String username,@RequestParam String name,@RequestParam String password){
        if (!service.exists(previousUsername)){
            return false;
        }
        service.modify(service.find(previousUsername), username,name,password);
        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public boolean postRegular(@RequestParam String username,@RequestParam String name,@RequestParam String password){
        if (service.exists(username) || regularuserService.find(username)!=null){
            return false;
        }
        service.create(username,name,password);
        return true;
    }
    @DeleteMapping(value = "/managers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUser(@RequestBody Regularuser u){
        return service.deleteUser(u.getUsername());
    }





}