
package cms.service;

import cms.DAO.RegularuserDao;
import cms.model.Archive;
import cms.model.Shipment;
import cms.service.ArchiveService;
import cms.service.RegularuserService;
import cms.service.ShipmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class RegularuserServiceTest {

    @PersistenceContext
    private TestEntityManager em;

    @Autowired
    private RegularuserDao dao;

    @Autowired
    private RegularuserService sut;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ArchiveService archiveService;



    public Shipment genshipment(){
        final Shipment shipment = new Shipment();
        shipment.setStatus("Delivering");
        shipment.setAssignedvehicle("666");
        shipment.setDestination("hell");
        shipment.setCargo("sinners");
        shipment.setId(69);
        return shipment;
    }
    @Test
    public void ShipmentFailedWorks(){
        final Shipment shipment =  genshipment();
        em.persist(shipment);

        Assert.assertNotEquals(shipment.getStatus(),"failed");
        sut.setShipmentFailed(69);
        Shipment res = shipmentService.find(69);
        Assert.assertEquals(res.getStatus(),"failed");



    }
    @Test
    public void setShipmentFinishedTest(){
        final Shipment shipment =  genshipment();
        em.persist(shipment);
        Shipment res = shipmentService.find(69);
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getId(),(Integer) 69);
        sut.setShipmentFinished(shipment.getId(),"noproblema");

        res = shipmentService.find(69);
        assertNull(res);
        Archive archive = archiveService.findArchivedShipment(69);
        Assert.assertNotNull(archive);
        Assert.assertEquals(archive.getId(),69);

    }
}
