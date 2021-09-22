package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    Optional<Fournisseur> findByCodeFournisseur(String codeFournisseur);

    Optional<Fournisseur> findByNomFournisseur(String nomFournisseur);
}
