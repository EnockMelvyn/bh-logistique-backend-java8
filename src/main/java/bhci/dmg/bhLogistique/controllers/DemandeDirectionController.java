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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.DemandeDirection;
import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.services.DemandeDirectionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/demandeDirection")
public class DemandeDirectionController {

	@Autowired
	DemandeDirectionService demandeDirectionService;
	
	@GetMapping("all")
	public ResponseEntity<List<DemandeDirection>> getAllDemandeDirection() {
        return new ResponseEntity<>( demandeDirectionService.getAllDemandeDirection(), HttpStatus.OK);
    }
	
	@GetMapping("status")
	public ResponseEntity<List<DemandeDirection>> getDemandeByStatus(@RequestParam String codeStatus) {
        return new ResponseEntity<>( demandeDirectionService.getDemandeDirectionByCodeStatus(codeStatus), HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<DemandeDirection> genererDemandeDirection(@RequestBody Direction direction) {
        return new ResponseEntity<>(demandeDirectionService.generateDemandeDirection(direction.getIdDirection()), HttpStatus.CREATED);
    }
	
	@PutMapping("sendToDmg")
    public ResponseEntity<DemandeDirection> sendDemandeDirectionToDmg(@RequestBody DemandeDirection demandeDirection) {
        return new ResponseEntity<>(demandeDirectionService.sendToDmg(demandeDirection), HttpStatus.OK);
    }
	
	@PutMapping("dmgValidate")
    public ResponseEntity<DemandeDirection> dmgValidateDemandeDirection(@RequestBody DemandeDirection demandeDirection) {
        return new ResponseEntity<>(demandeDirectionService.validationDmg(demandeDirection), HttpStatus.OK);
    }
}