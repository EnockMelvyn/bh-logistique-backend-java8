package bhci.dmg.bhLogistique.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bhci.dmg.bhLogistique.dao.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	Optional<Service> findByCodeService(String codeService);
}
