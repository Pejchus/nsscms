package cms.service;

import cms.model.Systemmanager;
import org.springframework.transaction.PlatformTransactionManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
@Component
public class SystemInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(SystemInitializer.class);

    /**
     * Default admin username
     */
    private static final String ADMIN_USERNAME = "user";

    private final SystemmanagerService service;

    private final VehicleService vehicleservice;

    private final PlatformTransactionManager txManager;

    @Autowired
    public SystemInitializer(SystemmanagerService service,
                             VehicleService vehicleservice, PlatformTransactionManager txManager) {
        this.service = service;
        this.vehicleservice = vehicleservice;
        this.txManager = txManager;
    }

    @PostConstruct
    private void initSystem() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        txTemplate.execute((status) -> {
            generateSystemmanager();
            return null;
        });
    }

    /**
     * Generates an admin account if it does not already exist.
     */
    private void generateSystemmanager() {
        /*
        final Systemmanager manager = new Systemmanager();
        manager.setUsername("users");
        manager.setFullname("Jiri Kara");
        manager.setPassword("users");
        LOG.info("Generated admin user with credentials " + manager.getUsername() + "/" + manager.getPassword());
        service.persist(manager);
        try {
            vehicleservice.createVehicle("FordExploreHer");
            vehicleservice.createVehicle("USV-UScreamTruck");
            vehicleservice.createVehicle("schuckwagen");
            vehicleservice.createVehicle("18squeler");
            vehicleservice.createVehicle("escalaid");
            vehicleservice.createVehicle("Chevy69");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
