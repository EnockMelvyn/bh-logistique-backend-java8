package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByCodeCategorie(String CodeCategorie);
    Categorie findByIdCategorie(Long idCategorie);
    
}
