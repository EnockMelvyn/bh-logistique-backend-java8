package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByCodeStatut(String CodeStatut);
    
}
