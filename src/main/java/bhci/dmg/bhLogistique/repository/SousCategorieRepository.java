package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Categorie;
import bhci.dmg.bhLogistique.dao.SousCategorie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long> {
    SousCategorie findByCodeSousCategorie(String CodeSousCategorie);
    SousCategorie findByIdSousCategorie(Long idSousCategorie);
    
    List<SousCategorie> findByCategorie(Categorie categorie);
    
}
