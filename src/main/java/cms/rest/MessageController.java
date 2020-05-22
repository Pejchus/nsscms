package cms.rest;

import cms.model.Message;
import cms.model.Vehicle;
import cms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/messages",method = RequestMethod.GET)
    public ResponseEntity<String> getmessages(@RequestParam String user,@RequestParam String role){
        String messages = messageService.getMessages(user,role);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public ResponseEntity<String> createMessage(@RequestParam String dispatcher,@RequestParam String driver,@RequestParam String sender,@RequestParam String content){
        messageService.createMessage(content,dispatcher,driver,sender);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @RequestMapping(value = "/message/delete",method = RequestMethod.GET)
    public ResponseEntity<String> delete(@RequestParam String id){
        messageService.delete(id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @RequestMapping(value = "/message/deleteold",method = RequestMethod.GET)
    public ResponseEntity<String> deleteold(@RequestParam String user){
        messageService.deleteold(user,"DISPATCH");
        return new ResponseEntity<>("true", HttpStatus.OK);
    }


}
