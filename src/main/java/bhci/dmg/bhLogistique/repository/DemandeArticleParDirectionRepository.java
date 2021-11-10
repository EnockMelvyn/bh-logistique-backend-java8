package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.DashboardDemande;
import bhci.dmg.bhLogistique.dao.DemandeArticleParDirection;
import bhci.dmg.bhLogistique.dao.Fournisseur;

@Repository
@EnableJpaRepositories
public interface DemandeArticleParDirectionRepository extends JpaRepository<DemandeArticleParDirection, Long>{
	
	List<DemandeArticleParDirection> findByIdDirectionAndStatut(Long idDirection, String statut);
	
	List<DemandeArticleParDirection> findByIdDirectionAndIdStatus(Long idDirection, Long idStatus);
}
