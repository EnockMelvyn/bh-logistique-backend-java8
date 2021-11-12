package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Livraison;
import bhci.dmg.bhLogistique.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    LivraisonService livraisonService;

    @GetMapping
    public ResponseEntity<List<Livraison>> getAllLivraisons(@RequestParam(required = false) String numeroBL) {

        try {
            List<Livraison> livraisons = new ArrayList<Livraison>();
            if (numeroBL == null) {
                livraisons = livraisonService.getAllLivraisons();
            }
            else {
                livraisons.add(livraisonService.getLivraisonByNumeroBl(numeroBL));
            }
            if (livraisons.isEmpty()) {
                return new ResponseEntity<>(livraisons, HttpStatus.OK);
            }

            return new ResponseEntity<>(livraisons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Livraison> createLivraison(@RequestBody Livraison livraison) {
        return new ResponseEntity<>(livraisonService.createLivraison(livraison), HttpStatus.CREATED);
    }

    @PutMapping("/{idLivraison}")
    public ResponseEntity<Livraison> updateLivraison(@PathVariable Long idLivraison, @RequestBody Livraison livraison) {
        return new ResponseEntity<>(livraisonService.updateLivraison(idLivraison, livraison), HttpStatus.OK);
    }

}
