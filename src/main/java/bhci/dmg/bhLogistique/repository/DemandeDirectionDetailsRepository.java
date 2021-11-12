package bhci.dmg.bhLogistique.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.DemandeDirection;
import bhci.dmg.bhLogistique.dao.DemandeDirectionDetails;

public interface DemandeDirectionDetailsRepository extends JpaRepository<DemandeDirectionDetails, Long> {

	List<DemandeDirectionDetails> findByArticle(Article article); 
	
	Optional<DemandeDirectionDetails> findByArticleAndDemandeDirection(Article article, DemandeDirection demandeDirection); 
}
