package bhci.dmg.bhLogistique.repository;

import bhci.dmg.bhLogistique.dao.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SortieRepository extends JpaRepository<Sortie, Long> {

    Optional<Sortie> findByReference(String reference);
}