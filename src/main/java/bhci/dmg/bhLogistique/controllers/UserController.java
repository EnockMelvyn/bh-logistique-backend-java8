package bhci.dmg.bhLogistique.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dto.ProfilPersonnelDto;
import bhci.dmg.bhLogistique.dto.UserInfoDto;
import bhci.dmg.bhLogistique.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<UserInfoDto> getInfosById(@RequestParam String email) {
		return new ResponseEntity<UserInfoDto>(userService.getUserInfosByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<UserInfoDto> getInfosById(@PathVariable Long id) {
		return new ResponseEntity<UserInfoDto>(userService.getUserInfosById(id), HttpStatus.OK);
	}
	
	@PostMapping(path="addProfilPersonnel")
	public ResponseEntity<UserInfoDto> addProfilPersonnel(@RequestBody ProfilPersonnelDto profPerso) {
		return new ResponseEntity<UserInfoDto>(userService.createProfilPersonnel(profPerso.getEmail(), profPerso.getCodesProfil()), HttpStatus.OK);
	}
	
}
