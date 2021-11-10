package bhci.dmg.bhLogistique.controllers;


import bhci.dmg.bhLogistique.dto.StatusDto;
import bhci.dmg.bhLogistique.services.StatutService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/status")
public class StatusController {

    @Autowired
    StatutService statutService;

    @GetMapping("/all")
    public ResponseEntity<List<StatusDto>> getAllStatus() {
       return new ResponseEntity<>(statutService.getAllStatus(), HttpStatus.OK);
    }
    
    @GetMapping("/status")
    public ResponseEntity<StatusDto> getAllStatus(@RequestParam String codeStatus) {
       return new ResponseEntity<>(statutService.getStatusByCode(codeStatus), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto) {
    	log.info("-- Création d'un status ...");
    	StatusDto createResponse = statutService.createStatus(statusDto);
    	log.info("-- Création terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<StatusDto> updateStatus(@RequestBody StatusDto statusDto) {
    	log.info("-- Mise à jour d'un status ...");
    	StatusDto createResponse = statutService.updateStatus(statusDto);
    	log.info("-- Mise à jour terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
}
