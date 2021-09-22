package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.SousFamille;
import bhci.dmg.bhLogistique.services.SousFamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sousFamilles")
public class SousFamilleController {

    @Autowired
    SousFamilleService sousFamilleService;

    @GetMapping
    public ResponseEntity<List<SousFamille>> getAllSousFamilles(@RequestParam(required = false) String codeSousFamille) {

        try {
            List<SousFamille> sousFamilles = new ArrayList<SousFamille>();
            if (codeSousFamille == null) {
                sousFamilles = sousFamilleService.getAllSousFamilles();
            }
            else {
                sousFamilles.add(sousFamilleService.getSousFamilleByCode(codeSousFamille));
            }
            if (sousFamilles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sousFamilles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idSousFamille}")
    public ResponseEntity<SousFamille> getSousFamilleById(@PathVariable Long idSousFamille){
        SousFamille sousFamille = new SousFamille();
        sousFamille= sousFamilleService.getSousFamilleById(idSousFamille);

        if (sousFamille == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sousFamille, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SousFamille> createSousFamille(@RequestBody SousFamille sousFamille) {
        return new ResponseEntity<>(sousFamilleService.createSousFamille(sousFamille), HttpStatus.CREATED);
    }

    @PutMapping("/{idSousFamille}")
    public ResponseEntity<SousFamille> updateFamille(@PathVariable Long idSousFamille, @RequestBody SousFamille sousFamille) {
        return new ResponseEntity<>(sousFamilleService.updateSousFamille(idSousFamille, sousFamille), HttpStatus.OK);
    }
}
