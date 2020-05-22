package cms.DAO;

import cms.model.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

@Repository
public class MessageDao extends baseDao {

    protected MessageDao() {
        super(Message.class);
    }

    public void createMessage(String content, String driver, String dispathcher, String sender) {
        Message m = new Message();
        m.setSender(sender);
        m.setContent(content);
        m.setDispatcher(dispathcher);
        m.setDriver(driver);
        m.setTime(new Timestamp(System.currentTimeMillis()));
        persist(m);
    }

    @OrderBy("Message.time DESC")
    public List<Message> getDriverMessages(String driver) {
            return em.createQuery("SELECT e FROM Message e WHERE e.driver=:driver").setParameter("driver", driver).getResultList();
    }

    @OrderBy("Message.time ASC ")
    public List<Message> getDriverMessages(String driver,String sender) {
        return em.createQuery("SELECT e FROM Message e WHERE e.driver=:driver and e.sender =:sender").setParameter("driver", driver).setParameter("sender",sender).getResultList();
    }
    @OrderBy("Message.time asc ")
    public List<Message> getDispatcherMessages(String dispatcher,String sender) {
        return em.createQuery("SELECT e FROM Message e WHERE e.dispatcher=:dispatcher and e.sender =:sender").setParameter("dispatcher", dispatcher).setParameter("sender",sender).getResultList();
    }


    public List<Message> getDispatcherMessages(String dispatcher) {
        return em.createQuery("SELECT e FROM Message e WHERE e.dispatcher=:dispatcher").setParameter("dispatcher", dispatcher).getResultList();
    }
}
