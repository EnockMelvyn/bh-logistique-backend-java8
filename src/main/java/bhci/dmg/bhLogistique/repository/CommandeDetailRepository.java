package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.CommandeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CommandeDetailRepository extends JpaRepository<CommandeDetail, Long> {

}
