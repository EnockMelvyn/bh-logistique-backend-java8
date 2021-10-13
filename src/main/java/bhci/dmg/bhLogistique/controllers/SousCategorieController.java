package bhci.dmg.bhLogistique.controllers;


import bhci.dmg.bhLogistique.dto.SousCategorieDto;
import bhci.dmg.bhLogistique.services.SousCategorieService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/sousCategorie")
public class SousCategorieController {

    @Autowired
    SousCategorieService sousCategorieService;

    @GetMapping("/all")
    public ResponseEntity<List<SousCategorieDto>> getAllSousCategorie() {
       return new ResponseEntity<>(sousCategorieService.getAllSousCategorie(), HttpStatus.OK);
    }
    
    @GetMapping("/{idCategorie}")
    public ResponseEntity<List<SousCategorieDto>> getSousCategorie(@PathVariable Integer idCategorie) {
    	log.info("idCategorie"+idCategorie );
       return new ResponseEntity<>(sousCategorieService.getSousCategorieByCategorie(idCategorie), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SousCategorieDto> createSousCategorie(@RequestBody SousCategorieDto sousCategorieDto) {
    	log.info("-- Création d'un sousCategorie ...");
    	SousCategorieDto createResponse = sousCategorieService.createSousCategorie(sousCategorieDto);
    	log.info("-- Création terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<SousCategorieDto> updateSousCategorie(@RequestBody SousCategorieDto sousCategorieDto) {
    	log.info("-- Mise à jour d'un sousCategorie ...");
    	SousCategorieDto createResponse = sousCategorieService.updateSousCategorie(sousCategorieDto);
    	log.info("-- Mise à jour terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
}
