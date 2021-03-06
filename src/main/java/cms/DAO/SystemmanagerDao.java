package cms.DAO;

import org.springframework.stereotype.Repository;
import cms.model.Systemmanager;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import cms.model.Regularuser;

@Repository
public class SystemmanagerDao extends BaseDao<Systemmanager> {

    @PersistenceContext
    private EntityManager em;


    protected SystemmanagerDao() {
        super(Systemmanager.class);
    }


    public Systemmanager find(String username){
        Objects.requireNonNull(username);
        return em.find(Systemmanager.class, username);
    }

    public boolean createUser(String username, String password, String fullName){
        Systemmanager user = new Systemmanager();
        user.setFullname(fullName);
        user.setPassword(password);
        user.setUsername(username);
        try {
            em.persist(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteUser(String username){
        Objects.requireNonNull(username);
        Regularuser user = em.find(Regularuser.class, username);
        try {
            em.getTransaction();
            em.remove(user);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean processShipment(Integer id, String licensePlate){
     /*   Objects.requireNonNull(id);
        Shipment s = em.find(Shipment.class, id);
        s.setVehicle(licensePlate);
        em.getTransaction();
        try{
            em.persist(s);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }*/
        return true;
    }

}
