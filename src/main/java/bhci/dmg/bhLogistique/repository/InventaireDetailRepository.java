package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.InventaireDetail;

@Repository
public interface InventaireDetailRepository extends JpaRepository<InventaireDetail, Long>{

}
