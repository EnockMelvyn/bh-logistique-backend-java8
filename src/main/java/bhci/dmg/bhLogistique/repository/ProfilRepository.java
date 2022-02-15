package bhci.dmg.bhLogistique.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bhci.dmg.bhLogistique.dao.Profil;

public interface ProfilRepository extends JpaRepository<Profil, Long> {

	Optional<Profil> findByCode(String code);
}
