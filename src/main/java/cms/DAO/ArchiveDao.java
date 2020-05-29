package cms.DAO;

import org.springframework.stereotype.Repository;
import cms.model.Archive;
import cms.model.Shipment;
import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class ArchiveDao extends BaseDao<Archive> {


    @PersistenceContext
    private EntityManager em;

    protected ArchiveDao() {
        super(Archive.class);
    }

    @OrderBy("Archive.id ASC")
    public List<Archive> getAll(){
        return em.createNamedQuery("Archive.findAllRecords", Archive.class)
                .getResultList();

    }

    public Archive findArchivedShipment(Integer id){
        Objects.requireNonNull(id);
        return em.find(Archive.class, id);
    }

    public Shipment findShipment(Integer id){
        Objects.requireNonNull(id);
        return em.find(Shipment.class, id);
    }


}
