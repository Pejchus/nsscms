package cms.service;

import cms.DAO.SystemmanagerDao;
import cms.model.Regularuser;
import cms.model.Systemmanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemmanagerService {

    private final SystemmanagerDao dao;

    @Autowired
    public SystemmanagerService(SystemmanagerDao dao) {
        this.dao = dao;
    }

    @Transactional
    public Systemmanager find(String username){
        return dao.find(username);
    }

    @Transactional
    public boolean createUser(String username, String password, String fullName, String license){
        return dao.createUser(username,password,fullName,license);

    }

    @Transactional
    public boolean deleteUser(String username){
        return dao.deleteUser(username);
    }

    @Transactional
    public boolean processShipment(Integer id, String licensePlate){
        return dao.processShipment(id,licensePlate);
    }

    @Transactional
    public boolean exists(String username){
        return dao.find(username) != null;
    }

    @Transactional
    public void persist(Systemmanager manager){
        dao.persist(manager);
    }



    @Transactional
    public boolean autentificate(String username, String password) {
        Systemmanager user = find(username);
        if(user==null){
            return false;
        }
        System.out.println(user.getPassword()+"="+password);
        if(user.getPassword().equals(password)){
            System.out.println("kokot");
            return true;
        }
        return false;
    }
    @Transactional
    public List<Systemmanager> findAll(){
        return dao.findAll();
    }

}
