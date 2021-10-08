package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    Optional<Demande> idDemande(String numRef);
    
    Optional<Demande> findByNumRef(String numRef);
    
    Optional<Demande> findByNumRefAndIsDeleted(String numRef, Boolean isDeleted);
    
    Demande findByIdDemande(Long idDemande);
    
    Demande findByIdDemandeAndIsDeleted(Long idDemande, Boolean isDeleted);

    List<Demande> findDemandesByDemandeur(String demandeur);

    List<Demande> findDemandesByStatutDemande(String statutDemande);
}
