package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.Inventaire;

@Repository
public interface InventaireRepository extends JpaRepository<Inventaire, Long>{

}
