package cms.service;

import cms.DAO.MessageDao;
import cms.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
