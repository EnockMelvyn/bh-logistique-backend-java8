package bhci.dmg.bhLogistique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.DashboardDemande;
import bhci.dmg.bhLogistique.services.DashboardDemandeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	DashboardDemandeService dashboardDemandeService;
	
	@GetMapping("demandes")
	public ResponseEntity<List<DashboardDemande>> dashboardDemande(@RequestParam String demandeur){
		return new ResponseEntity<>(dashboardDemandeService.getDashboardDemande(demandeur), HttpStatus.OK);
	}
}
