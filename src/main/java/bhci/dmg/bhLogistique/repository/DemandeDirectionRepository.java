package bhci.dmg.bhLogistique.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.DemandeDirection;
import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.dao.Status;

public interface DemandeDirectionRepository extends JpaRepository<DemandeDirection, Long> {
	
	List<DemandeDirection> findByStatus(Status status);
	
	Optional<DemandeDirection> findByDirection(Direction direction);
	
	Optional<DemandeDirection> findByDirectionAndStatus(Direction direction, Status status);
}
