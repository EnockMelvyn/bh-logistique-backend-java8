package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.CommandeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CommandeDetailRepository extends JpaRepository<CommandeDetail, Long> {

}
