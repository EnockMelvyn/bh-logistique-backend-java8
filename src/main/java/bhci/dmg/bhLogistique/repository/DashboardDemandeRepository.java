package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.DashboardDemande;

@Repository
@EnableJpaRepositories
public interface DashboardDemandeRepository extends JpaRepository<DashboardDemande, Long>{
	
	List<DashboardDemande> findByDemandeur(String demandeur);
}
