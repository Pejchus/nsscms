package cms.DAO;

import org.springframework.stereotype.Repository;
import cms.model.Vehicle;
import cms.model.Regularuser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class VehicleDao extends BaseDao<Vehicle> {

    @PersistenceContext
    private EntityManager em;

    public VehicleDao() {
        super(Vehicle.class);
    }

    public Vehicle createVehicle(String licensePlate,String driver) throws Exception{
            Vehicle v = new Vehicle();
            v.setAvailability(true);
            v.setLicenseplate(licensePlate);
            v.setDriver(driver);
            persist(v);
            return v;
    }

    public Vehicle find(String id){
        Objects.requireNonNull(id);
        return em.find(Vehicle.class, id);
    }

    public Vehicle findbyId(Integer id){
        return  em.createQuery("select s from  Vehicle s where s.id = :id" , Vehicle.class).setParameter("id", Integer.valueOf(id)).getSingleResult();
    }



    public void assignVehicle(String licensePlate, String username){
        Regularuser user = em.find(Regularuser.class, username);
       // user.setVehicleid(licensePlate);
        em.persist(user);
    }

    public void destroyVehicle(String licensePlate){
        Vehicle v = em.find(Vehicle.class, licensePlate);
        //em.getTransaction();
        em.remove(v);
       // em.getTransaction().commit();
    }

    public List<Vehicle> findAllAvailable(){
        return em.createQuery("select s from  Vehicle s where s.driver is null ").getResultList();
    }
    public  void create(String username, String name, String licence, String password){

    }
}
