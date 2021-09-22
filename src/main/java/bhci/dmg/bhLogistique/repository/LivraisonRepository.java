package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface LivraisonRepository extends JpaRepository<Livraison, Long>{

    Optional<Livraison> findByNumeroBl(String numeroBl);
}