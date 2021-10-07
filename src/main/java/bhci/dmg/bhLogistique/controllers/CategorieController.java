package bhci.dmg.bhLogistique.controllers;


import bhci.dmg.bhLogistique.dto.CategorieDto;
import bhci.dmg.bhLogistique.services.CategorieService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/all")
    public ResponseEntity<List<CategorieDto>> getAllCategorie() {
       return new ResponseEntity<>(categorieService.getAllCategorie(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategorieDto> createCategorie(@RequestBody CategorieDto categorieDto) {
    	log.info("-- Création d'un categorie ...");
    	CategorieDto createResponse = categorieService.createCategorie(categorieDto);
    	log.info("-- Création terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<CategorieDto> updateCategorie(@RequestBody CategorieDto categorieDto) {
    	log.info("-- Mise à jour d'un categorie ...");
    	CategorieDto createResponse = categorieService.updateCategorie(categorieDto);
    	log.info("-- Mise à jour terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
}
