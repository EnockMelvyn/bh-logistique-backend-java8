package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilleRepository extends JpaRepository<Famille, Long>, JpaSpecificationExecutor<Famille> {

    Optional<Famille> findByCodeFamille(String codeFamille);

}