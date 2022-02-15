package bhci.dmg.bhLogistique.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

	Optional<Personnel> findByEmail(String email);
	Optional<Personnel> findByMatricule(String matricule);
}
