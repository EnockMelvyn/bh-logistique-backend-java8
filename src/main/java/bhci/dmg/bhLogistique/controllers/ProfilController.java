package bhci.dmg.bhLogistique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.Profil;
import bhci.dmg.bhLogistique.repository.ProfilRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/profil")
public class ProfilController {
	
	@Autowired
	ProfilRepository profilRepo;
	
	@GetMapping
	public ResponseEntity<List<Profil>> getAllProfils() {
		return new ResponseEntity<>(profilRepo.findAll(), HttpStatus.OK);
	}

}
