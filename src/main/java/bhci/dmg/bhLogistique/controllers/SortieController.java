package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Sortie;
import bhci.dmg.bhLogistique.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/sorties")
public class SortieController {
    @Autowired
    SortieService sortieService;

    @GetMapping
    public ResponseEntity<List<Sortie>> getAllSorties(@RequestParam(required = false) String reference) {

        try {
            List<Sortie> sorties = new ArrayList<Sortie>();
            if (reference == null) {
                sorties = sortieService.getAllSorties();
            }
            else {
                sorties.add(sortieService.getSortieByReference(reference));
            }
            if (sorties.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sorties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idSortie}")
    public ResponseEntity<Sortie> getSortieById(@PathVariable Long idSortie){
        Sortie sortie = new Sortie();
        sortie= sortieService.getSortieById(idSortie);

        if (sortie == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sortie, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie) {
        return new ResponseEntity<>(sortieService.createSortie(sortie), HttpStatus.CREATED);
    }

}
