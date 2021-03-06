package bhci.dmg.bhLogistique.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.Direction;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

	 Optional<Direction> findByCodeDirection(String codeDirection);
}
