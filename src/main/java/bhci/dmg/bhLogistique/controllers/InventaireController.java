package bhci.dmg.bhLogistique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.Inventaire;
import bhci.dmg.bhLogistique.services.InventaireService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/inventaires")
public class InventaireController {

	@Autowired
	InventaireService invService;
	
	@GetMapping
	public ResponseEntity<List<Inventaire>> getAllInventaire(){
		return new ResponseEntity<>(invService.getAllInventaire(), HttpStatus.OK);
	}
	
	@PostMapping("/generate")
	public ResponseEntity<Inventaire> genererInventaire(@RequestBody Inventaire inv){
		return new ResponseEntity<>(invService.generateInventaire(inv),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/maj")
	public ResponseEntity<Inventaire> MajInventaire(@RequestBody Inventaire inv){
		return new ResponseEntity<>(invService.majInventaire(inv),HttpStatus.OK);
	}
	
	@PutMapping("/validate")
	public ResponseEntity<Inventaire> validerInventaire(@RequestBody Inventaire inv){
		return new ResponseEntity<>(invService.validerInventaire(inv),HttpStatus.OK);
	}
}
