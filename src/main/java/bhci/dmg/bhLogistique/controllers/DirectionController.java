package bhci.dmg.bhLogistique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.services.DirectionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/directions")
public class DirectionController {

	@Autowired
	DirectionService directionService;
	
	@GetMapping
	public ResponseEntity<List<Direction>> getAllDirections() {
		return new ResponseEntity<>( directionService.getAllDirections(), HttpStatus.FOUND);
	}
}
