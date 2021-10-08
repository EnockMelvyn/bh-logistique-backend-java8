package bhci.dmg.bhLogistique.controllers;


import bhci.dmg.bhLogistique.dto.TypeDto;
import bhci.dmg.bhLogistique.services.TypeService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeDto>> getAllType() {
       return new ResponseEntity<>(typeService.getAllType(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TypeDto> createType(@RequestBody TypeDto typeDto) {
    	log.info("-- Création d'un type ...");
    	TypeDto createResponse = typeService.createType(typeDto);
    	log.info("-- Création terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<TypeDto> updateType(@RequestBody TypeDto typeDto) {
    	log.info("-- Mise à jour d'un type ...");
    	TypeDto createResponse = typeService.updateType(typeDto);
    	log.info("-- Mise à jour terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
}
