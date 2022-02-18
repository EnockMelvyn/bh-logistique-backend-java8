package bhci.dmg.bhLogistique.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.DemandeDirection;
import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.dao.Status;

public interface DemandeDirectionRepository extends JpaRepository<DemandeDirection, Long> {
	
	List<DemandeDirection> findByStatus(Status status);
	
	List<DemandeDirection> findByDirection(Direction direction);

	List<DemandeDirection> findByDateDemandeBetween(LocalDateTime dateDemandeStart, LocalDateTime dateDemandeEnd);

	//Optional<DemandeDirection> findByDirectionAndStatus(Direction direction, Status status);
	
	List<DemandeDirection> findByDirectionAndStatus(Direction direction, Status status);
	
	
}
