package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Famille;
import bhci.dmg.bhLogistique.dao.SousFamille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SousFamilleRepository extends JpaRepository<SousFamille, Long>{

    Optional<SousFamille> findByCodeSousFamille(String codeSousFamille);

    List<SousFamille> findByFamille(Famille famille);

}