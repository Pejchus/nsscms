package cms.service;


import cms.DAO.ShipmentDao;
import cms.DAO.VehicleDao;
import cms.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleDao dao;

    @Autowired
    public VehicleService(VehicleDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void createVehicle (String licensePlate)throws Exception{
        dao.createVehicle(licensePlate);
    }

    @Transactional
    public List<Vehicle> findall(){
       return dao.findAll();
    }
    @Transactional
    public Vehicle find(String licensePlate){
        return dao.find(licensePlate);
    }
    @Transactional
    public Vehicle findById(int licensePlate){
        return dao.findbyId(licensePlate);
    }

    @Transactional
    public void assignVehicle(String licensePlate, String username){
        dao.assignVehicle(licensePlate,username);
    }
    @Transactional
    public void destroyVehicle(String licensePlate){
        dao.destroyVehicle(licensePlate);
    }
    @Transactional
    public List<Vehicle> findallavailable() {
        return dao.findAllAvailable();

    }
}
