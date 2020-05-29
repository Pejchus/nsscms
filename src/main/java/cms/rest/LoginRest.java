package cms.rest;

import cms.service.RegularuserService;
import cms.service.SystemmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class LoginRest {
    private final RegularuserService regularuserService;
    private final SystemmanagerService systemmanagerService;


    @Autowired
    public LoginRest(RegularuserService regularuserService, SystemmanagerService systemmanagerService) {
        this.regularuserService = regularuserService;
        this.systemmanagerService = systemmanagerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (regularuserService.autentificate(username, password)) {
            return new ResponseEntity<>("DRIVER", HttpStatus.OK);
        }
        if (systemmanagerService.autentificate(username, password)) {
            return new ResponseEntity<>("DISPATCH", HttpStatus.OK);
        }
        return new ResponseEntity<>("LOGIN", HttpStatus.OK);
    }
}
