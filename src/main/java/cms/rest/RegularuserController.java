package cms.rest;

import cms.model.Regularuser;
import cms.model.Shipment;
import cms.service.Coder;
import cms.service.RegularuserService;
import cms.service.SystemmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regularuser")
public class RegularuserController {

    private final RegularuserService service;
    private final SystemmanagerService systemmanagerService;
    private final Coder coder;


    @Autowired
    public RegularuserController(RegularuserService service, SystemmanagerService systemmanagerService, Coder coder) {
        this.service = service;
        this.systemmanagerService = systemmanagerService;

        this.coder = coder;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Regularuser getUser(@PathVariable String id) {
        return service.find(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll(){
        return new ResponseEntity<>(Coder.codeRegular(service.findAll()),HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public boolean postRegular(@RequestParam String username,@RequestParam String name,@RequestParam String password, @RequestParam String licence, @RequestParam String vehicle){
        if (service.find(username)!=null || systemmanagerService.exists(username)){
            return false;
        }
        service.create(username,name,password,vehicle,licence);
        return true;
    }

    @GetMapping(value = "/full",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAllFull(){
        return new ResponseEntity<String>(coder.codeRegularFull(service.findAll()),HttpStatus.OK);
    }
    @GetMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@RequestParam String username){
       Regularuser regularuser =  service.find(username);
       if(regularuser==null){
           return false;
       }
       service.delete(regularuser);
       return true;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public boolean modify(@RequestParam String previoususername,@RequestParam String username,@RequestParam String name,@RequestParam String password, @RequestParam String licence, @RequestParam String vehicle){
        System.out.println(previoususername);
        Regularuser regularuser =  service.find(previoususername.trim());
        if (previoususername==null) {
            return false;
        }
        service.modify(regularuser,username,name,password,vehicle,licence);
        return true;
    }


    @PutMapping(value = "/regularsers/finished", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setfinished(String description,@RequestBody Shipment shipment){

        service.setShipmentFinished(shipment.getId(),description);
    }



    @PutMapping(value = "/regularsers/failed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setfailed(@RequestBody Shipment shipment) {
        service.setShipmentFailed(shipment.getId());
    }



}

