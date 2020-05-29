package cms.service;

import cms.DAO.SystemmanagerDao;
import cms.model.Regularuser;
import cms.model.Systemmanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SystemmanagerService {

    private final SystemmanagerDao dao;

    @Autowired
    public SystemmanagerService(SystemmanagerDao dao) {
        this.dao = dao;
    }

    @Transactional
    public Systemmanager find(String username) {
        return dao.find(username);
    }

    @Transactional
    public boolean createUser(String username, String password, String fullName) {
        return dao.createUser(username, password, fullName);

    }

    @Transactional
    public boolean deleteUser(String username) {
        return dao.deleteUser(username);
    }

    @Transactional
    public boolean processShipment(Integer id, String licensePlate) {
        return dao.processShipment(id, licensePlate);
    }

    @Transactional
    public boolean exists(String username) {
        return dao.find(username) != null;
    }

    @Transactional
    public void persist(Systemmanager manager) {
        dao.persist(manager);
    }


    @Transactional
    public boolean autentificate(String username, String password) {
        Systemmanager user = find(username);
        if (user == null) {
            return false;
        }
        System.out.println(user.getPassword() + "=" + password);
        if (user.getPassword().equals(password)) {
            System.out.println("kokot");
            return true;
        }
        return false;
    }

    @Transactional
    public List<Systemmanager> findAll() {
        return dao.findAll();
    }

    public void create(String username, String name, String password) {
        dao.createUser(username, password, name);
    }

    public void delete(Systemmanager regularuser) {
        dao.remove(regularuser);
    }

    public void modify(Systemmanager systemmanager, String username, String name, String password) {
        systemmanager.setFullname(name);
        systemmanager.setPassword(password);
        systemmanager.setUsername(username);
    }
}
