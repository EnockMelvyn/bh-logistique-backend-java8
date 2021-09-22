package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.dao.DemandeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeArticleRepository extends JpaRepository<DemandeArticle, Long> {

    List<DemandeArticle> findByDemande (Demande demande);


}