package cms.service;


import cms.DAO.RegularuserDao;
import cms.DAO.VehicleDao;
import cms.model.Regularuser;
import cms.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "vehicles")
public class VehicleService {

    private final VehicleDao dao;
    private final RegularuserDao regularuserDao;

    @Autowired
    public VehicleService(VehicleDao dao, RegularuserDao regularuserDao) {
        this.dao = dao;
        this.regularuserDao = regularuserDao;
    }

    @Transactional
    public void createVehicle(String licensePlate, String user) throws Exception {
        dao.createVehicle(licensePlate, user);
    }


    @Transactional
    public List<Vehicle> findall() {
        return dao.findAll();
    }

    @Transactional
    public Vehicle find(String licensePlate) {
        return dao.find(licensePlate);
    }

    @Transactional
    public Vehicle findById(Integer id) {
        return dao.findbyId(id);
    }

    @Transactional
    public void assignVehicle(String vehicle, String username) {
        Vehicle v = dao.find(vehicle);
        v.setDriver(username);
        // dao.update(v);
    }

    @Transactional
    public void destroyVehicle(String licensePlate) {
        dao.destroyVehicle(licensePlate);
    }

    @Transactional
    public List<Vehicle> findallavailable() {
        return dao.findAllAvailable();

    }
}
