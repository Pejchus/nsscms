package cms.DAO;

import org.springframework.stereotype.Repository;
import cms.model.Vehicle;
import cms.model.Regularuser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class VehicleDao extends baseDao<Vehicle>{

    @PersistenceContext
    private EntityManager em;

    public VehicleDao() {
        super(Vehicle.class);
    }

    public void createVehicle(String licensePlate) throws Exception{
            Vehicle v = new Vehicle();
            v.setAvailability(true);
            v.setLicenseplate(licensePlate);
            em.persist(v);
    }

    public Vehicle find(String id){
        Objects.requireNonNull(id);
        return em.find(Vehicle.class, id);
    }

    public Vehicle findbyId(int id){
        return  em.createQuery("select s from  Vehicle s where s.id = :id" , Vehicle.class).setParameter("id", Integer.valueOf(id)).getSingleResult();
    }

    public void assignVehicle(String licensePlate, String username){
        Regularuser user = em.find(Regularuser.class, username);
       // user.setVehicleid(licensePlate);
        em.persist(user);
    }

    public void destroyVehicle(String licensePlate){
        Vehicle v = em.find(Vehicle.class, licensePlate);
        em.getTransaction();
        em.remove(v);
        em.getTransaction().commit();
    }
}
