package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.Personnel;
import bhci.dmg.bhLogistique.dao.ProfilPersonnel;

@Repository
public interface ProfilPersonnelRepository extends JpaRepository<ProfilPersonnel, Long> {
	
	long deleteByPersonnel (Personnel personnel);
}
