package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.dao.Personnel;
import bhci.dmg.bhLogistique.dao.ProfilPersonnel;
import bhci.dmg.bhLogistique.dao.Profil;
import bhci.dmg.bhLogistique.dto.UserInfoDto;
import bhci.dmg.bhLogistique.repository.DirectionRepository;
import bhci.dmg.bhLogistique.repository.PersonnelRepository;
import bhci.dmg.bhLogistique.repository.ProfilPersonnelRepository;
import bhci.dmg.bhLogistique.repository.ProfilRepository;
import bhci.dmg.bhLogistique.repository.ServiceRepository;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserService {

	@Autowired
	PersonnelRepository personnelRepo;
	
	@Autowired
	DirectionRepository directionRepo;
	
	@Autowired
	ServiceRepository serviceRepo;
	
	@Autowired
	ProfilPersonnelRepository profilPersoRepo;
	
	@Autowired
	ProfilRepository profilRepo;
	
	public UserInfoDto getUserInfosById(Long id) {
		UserInfoDto userToReturn = new UserInfoDto();
		
		Personnel perso = personnelRepo.findById(id).orElse(null);
		
		if (perso == null) {
			throw new IllegalStateException("Utilisateur non reconnu");
		}
		
		List<String> profils = new ArrayList<String>();
		
		perso.getProfils().forEach(profilPerso -> {
			profils.add(profilPerso.getProfil().getCode());
		});
		
		userToReturn.setDirectionId(perso.getDirection().getIdDirection());
		userToReturn.setProfilesCode(profils);
		return userToReturn;
	}
	
	public UserInfoDto getUserInfosByEmail(String email) {
		UserInfoDto userToReturn = new UserInfoDto();
		
		Personnel perso = personnelRepo.findByEmail(email).orElse(null);
		
		if (perso == null) {
			throw new IllegalStateException("Utilisateur non reconnu");
		}
		
		List<String> profils = new ArrayList<String>();
		
		perso.getProfils().forEach(profilPerso -> {
			profils.add(profilPerso.getProfil().getCode());
		});
		
		if(perso.getDirection().getCodeDirection().equals("DBD") && perso.getService().getCodeService() != null ) {
			log.info("Modification de la direction cas d'une agence");
			userToReturn.setDirectionId(directionRepo.findByCodeDirection(perso.getService().getCodeService()).get().getIdDirection() );
		} else {
			userToReturn.setDirectionId(perso.getDirection().getIdDirection());
		}
		
		userToReturn.setProfilesCode(profils);
		return userToReturn;
	}
	
	@Transactional
	public UserInfoDto createProfilPersonnel(String email, List<String> codesProfil) {
		UserInfoDto userToReturn = new UserInfoDto();
		
		
		Personnel perso = personnelRepo.findByEmail(email).orElse(null);
		if (perso == null) {
			throw new IllegalStateException("Utilisateur non reconnu");
		}
		perso.getProfils().clear();
		//profilPersoRepo.deleteByPersonnel(perso);
		//System.out.println(profilPersoRepo.deleteByPersonnel(perso));
		/*Personnel perso = personnelRepo.findById(idPerso).orElse(null);
		if (perso == null) {
			throw new IllegalStateException("Utilisateur non reconnu");
		}*/
		
		List<ProfilPersonnel> profPersos = new ArrayList<>();
		codesProfil.forEach(element -> {
			ProfilPersonnel profPerso = new ProfilPersonnel();
			Profil profil = profilRepo.findByCode(element).get();
			profPerso.setPersonnel(perso);
			profPerso.setProfil(profil);
			profPersos.add(profPerso);

			//profilPersoRepo.save(profPerso);
		});
		
		profilPersoRepo.saveAll(profPersos);
		
		List<String> profils = new ArrayList<>();
		perso.getProfils().forEach(profilPerso -> {
			profils.add(profilPerso.getProfil().getCode());
		});
		
		userToReturn.setDirectionId(perso.getDirection().getIdDirection());
		userToReturn.setProfilesCode(profils);
		return userToReturn;
	}
}
