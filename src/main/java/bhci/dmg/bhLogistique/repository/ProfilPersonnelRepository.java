package bhci.dmg.bhLogistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.ProfilPersonnel;

public interface ProfilPersonnelRepository extends JpaRepository<ProfilPersonnel, Long> {
	
}
