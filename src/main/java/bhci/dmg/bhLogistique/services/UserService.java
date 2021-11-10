package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Personnel;
import bhci.dmg.bhLogistique.dto.UserInfoDto;
import bhci.dmg.bhLogistique.repository.PersonnelRepository;

@Service
public class UserService {

	@Autowired
	PersonnelRepository personnelRepo;
	
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
		
		userToReturn.setDirectionId(perso.getDirection().getIdDirection());
		userToReturn.setProfilesCode(profils);
		return userToReturn;
	}
}
