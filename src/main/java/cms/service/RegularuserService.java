package cms.service;

import cms.DAO.ArchiveDao;
import cms.DAO.RegularuserDao;
import cms.DAO.ShipmentDao;
import cms.model.Regularuser;
import cms.model.Shipment;
import cms.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegularuserService {

    private final RegularuserDao dao;
    private final ArchiveDao archivedao;
    private final ShipmentDao shipmentdao;
    private final VehicleService vehicleService;
    private final ShipmentService shipmentService;

    @Autowired
    public RegularuserService(RegularuserDao dao, ArchiveDao archivedao, ShipmentDao shipmentdao, VehicleService vehicleService, ShipmentDao shipmentDao, ShipmentService shipmentService) {
        this.archivedao=archivedao;
        this.shipmentdao=shipmentdao;
        this.dao = dao;
        this.vehicleService = vehicleService;
        this.shipmentService = shipmentService;
    }

    @Transactional
    public void delete(Regularuser user){
        vehicleService.assignVehicle(vehicleService.findById(user.getVehicleid()).getLicenseplate(),null);
        user.setVehicleid(null);
        user.setPassword(null);
        user.setAvailibility(false);
        dao.update(user);
        shipmentService.setfinish(user.getUsername());
    }

    @Transactional
    public void modify(Regularuser user,String username, String name,String password,String vehicle,String licence){
        user.setUsername(username);
        user.setLicensenumber(licence);
        user.setFullname(name);
        if(!password.equals("")){
            user.setPassword(password);
        }System.out.println(vehicle);
        if(user.getVehicleid()!=null) {
            unassigne(user);
        }
        if(vehicle!=null &&!vehicle.equals("null") && !vehicle.equals("none")) {
            Vehicle v = vehicleService.find(vehicle);
            user.setVehicleid(v.getId());
            user.setAvailibility(true);
            vehicleService.assignVehicle(vehicle,username);
        }else {

            user.setAvailibility(false);
            user.setVehicleid(null);
        }
        dao.update(user);
    }

    private void unassigne(Regularuser user){
        try {
            vehicleService.assignVehicle(vehicleService.findById(user.getVehicleid()).getLicenseplate(),null);
        }catch (Exception e){
        }
    }


    @Transactional
    public Regularuser find(String username){
        return dao.find(username);
    }

    @Transactional
    public List<Regularuser> findAll(){
        return dao.findAll();
    }

    @Transactional
    public void setShipmentFinished(Integer id, String description){
        dao.setShipmentFinished(id);
        Shipment shipment = shipmentdao.find(id);
        shipmentdao.deleteShipment(id);
    }
    @Transactional
    public void setShipmentFailed(Integer id){
        dao.setShipmentFailed(id);
    }

    @Transactional
    public List<Regularuser> findAvailable(){
        return dao.findAvailable();
    }
    @Transactional
    public void assignVehicle(String username,String vehicle){
        Regularuser user = find(username);
        modify(user,user.getUsername(),user.getFullname(),user.getPassword(),vehicle,user.getLicensenumber());
    }

    @Transactional
    public void create(String username, String name,String password,String vehicle,String licence){

        Vehicle v = vehicleService.find(vehicle);
        if(v!=null) {
            dao.create(username, name, v.getId(), licence, password);
            v.setDriver(username);
            v.setAvailability(false);
        }else {dao.create(username,name,licence,password);}

    }

    @Transactional
    public boolean autentificate(String username,String password){
        Regularuser user= dao.find(username);
        if(user==null){
            return false;
        }
        System.out.println(user.getPassword()+"="+password);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public List<Regularuser> findTruckless() {
       return dao.truckLess();
    }
}
