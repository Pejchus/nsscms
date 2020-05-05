package cms.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("Message")
public class Message {

    private String driver;
    private String dispatcher;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String sender;
    private String content;
    private Timestamp time;


    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDriver() {
        return driver;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public Integer getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }
}