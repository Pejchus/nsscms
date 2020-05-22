package cms.service;

import cms.DAO.MessageDao;
import cms.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    public boolean createMessage(String content,String disapatcher,String driver,String sender){
        messageDao.createMessage(content,driver,disapatcher,sender);
        return true;
    }

    @Transactional
    public String getMessages(String user,String role){
        if(role.equals("DISPATCH")){
            return Coder.codeDispatchMessage(messageDao.getDispatcherMessages(user));
        }else if(role.equals("DRIVER")){
            return Coder.codeDriverMessage(messageDao.getDriverMessages(user));
        }
        else return "";
    }

    @Transactional
    public void delete(String id) {
        messageDao.remove(messageDao.find(Integer.valueOf(id)));
    }

    @Transactional
    public void deleteold(String user,String role){
        System.out.println("kokot");
        List<Message> messages;
        if(role.equals("DISPATCH")){
            System.out.println("kokot1");
            messages = messageDao.getDispatcherMessages(user);
        }else if(role.equals("DRIVER")){
            messages = messageDao.getDriverMessages(user);
        }
        else {return;}
        System.out.println("kokot");
        for(Message m:messages){
            System.out.println(m.getTime().toLocalDateTime()+" "+LocalDateTime.now().minusDays(7)+" "+(m.getTime().toLocalDateTime().compareTo(LocalDateTime.now().minusDays(7))));
            if(m.getTime().toLocalDateTime().compareTo(LocalDateTime.now().minusDays(7))<0) {
                messageDao.remove(m);
            }
        }
    }
}
