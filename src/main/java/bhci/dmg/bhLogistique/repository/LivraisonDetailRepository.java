package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.LivraisonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LivraisonDetailRepository extends JpaRepository<LivraisonDetail, Long> {

}