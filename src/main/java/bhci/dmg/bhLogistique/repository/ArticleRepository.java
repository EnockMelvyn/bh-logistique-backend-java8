package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.SousFamille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByCodeArticle(String codeArticle);
    
    Article findByIdArticle(Long idArticle);
    
    List<Article> findBySousFamille(SousFamille sousfamille);

    Optional<Article> findByLibelleArticle(String libelleArticle);

}