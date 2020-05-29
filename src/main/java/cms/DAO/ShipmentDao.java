package cms.DAO;


import org.springframework.stereotype.Repository;
import cms.model.Shipment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class ShipmentDao extends BaseDao<Shipment> {

    @PersistenceContext
    private EntityManager em;

    protected ShipmentDao() {
        super(Shipment.class);
    }

    public Shipment find(Integer id){
        Objects.requireNonNull(id);
        return em.find(Shipment.class, id);
    }
    public List<Shipment> findAll(){
        return em.createQuery("SELECT s FROM Shipment s WHERE s.id IS NOT NULL", Shipment.class).getResultList();
    }

    public List<Shipment> findByStatus(String status,String driver){
        driver=driver.trim();
        driver = "%" + driver + "%";
        System.out.println(driver);
        return em.createQuery("SELECT s FROM Shipment s WHERE s.status like :status and s.driver like :driver", Shipment.class).setParameter("status",status).setParameter("driver",driver).getResultList();
    }
    public boolean setStatus(String status, Integer id){
        Shipment s = find(id);
        s.setStatus(status);
        try {
            em.persist(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteShipment(Integer id){
        Shipment s = find(id);
        em.getTransaction();
        try {
            em.remove(s);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void createShipment(String cargo,  String destination,String vehicle,String date){
        Shipment s = new Shipment();
        s.setStatus("active");
        s.setDestination(destination);
        s.setCargo(cargo);
        s.setDriver(vehicle);
        s.setShippingdate(date);
        persist(s);
    }

}
