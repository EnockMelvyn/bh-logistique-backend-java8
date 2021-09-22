package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Famille;
import bhci.dmg.bhLogistique.services.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/familles")
public class FamilleController {

    @Autowired
    FamilleService familleService;

    @GetMapping
    public ResponseEntity<List<Famille>> getAllFamilles(@RequestParam(required = false) String codeFamille) {

        try {
            List<Famille> familles = new ArrayList<Famille>();
            if (codeFamille == null) {
                familles = familleService.getAllFamilles();
            }
            else {
                familles.add(familleService.getFamilleByCode(codeFamille));
            }
            if (familles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(familles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idFamille}")
    public ResponseEntity<Famille> getFamilleById(@PathVariable Long idFamille){
        Famille famille = new Famille();
        famille= familleService.getFamilleById(idFamille);

        if (famille == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(famille, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Famille> createFamille(@RequestBody Famille famille) {
        return new ResponseEntity<>(familleService.createFamille(famille), HttpStatus.CREATED);
    }

    @PutMapping("/{idFamille}")
    public ResponseEntity<Famille> updateFamille(@PathVariable Long idFamille, @RequestBody Famille famille) {
        return new ResponseEntity<>(familleService.updateFamille(idFamille, famille), HttpStatus.OK);
    }
}
