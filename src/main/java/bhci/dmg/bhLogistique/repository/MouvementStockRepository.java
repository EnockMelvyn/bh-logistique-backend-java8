package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock,Long> {

    List<MouvementStock> findByArticle(Article article);

    List<MouvementStock> findByDateMouvement(LocalDate dateMouvement);


}
