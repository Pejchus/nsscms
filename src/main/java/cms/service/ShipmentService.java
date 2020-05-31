package cms.service;

import cms.DAO.RegularuserDao;
import cms.DAO.ShipmentDao;
import cms.model.Regularuser;
import cms.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "shipments")
public class ShipmentService {

    private final ShipmentDao dao;
    private final RegularuserDao regularuserDao;

    @Autowired
    public ShipmentService(ShipmentDao dao, RegularuserDao regularuserService) {
        this.dao = dao;
        this.regularuserDao = regularuserService;
    }

    @Transactional(readOnly = true)
    public Shipment find(Integer id) {
        return dao.find(id);
    }

    @Transactional
    public List<Shipment> findAll() {
        return dao.findAll();
    }


    @Transactional
    public boolean setStatus(String status, Integer id) {
        return dao.setStatus(status, id);
    }

    @Transactional
    public boolean deleteShipment(Integer id) {
        return dao.deleteShipment(id);
    }


    @Transactional
    public void createShipment(String cargo, String destination, String driver, String date) {
        Regularuser user = regularuserDao.find(driver);
        user.setAvailibility(false);
        regularuserDao.update(user);
        dao.createShipment(cargo, destination, driver, date);
    }

    @Transactional
    public List<Shipment> findactive(String driver) {
        return dao.findByStatus("active", driver);
    }

    @Transactional
    public List<Shipment> findfinish(String driver) {
        return dao.findByStatus("finished", driver);
    }

    @Transactional
    public void setfinish(String driver) {
        List<Shipment> shipments = dao.findByStatus("active", driver);
        for (Shipment s : shipments) {
            System.out.println("kokot");
            s.setStatus("finished");
            dao.update(s);
        }
    }

}
