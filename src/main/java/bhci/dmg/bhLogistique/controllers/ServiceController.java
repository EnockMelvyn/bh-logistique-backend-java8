package bhci.dmg.bhLogistique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.Service;
import bhci.dmg.bhLogistique.services.ServiceService;

@RestController
@CrossOrigin
@RequestMapping("service")
public class ServiceController {

	@Autowired
	ServiceService servService;
	
	public ResponseEntity<List<Service>> getAllServices(){
		return new ResponseEntity<>(servService.getAllServices(), HttpStatus.OK);
	}
}
