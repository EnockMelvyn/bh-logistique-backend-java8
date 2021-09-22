package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Fournisseur;
import bhci.dmg.bhLogistique.services.ArticleService;
import bhci.dmg.bhLogistique.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    FournisseurService fournisseurService;

    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs(@RequestParam(required = false) String codeFournisseur) {

        try {
            List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
            if (codeFournisseur == null) {
                fournisseurs = fournisseurService.getAllFournisseurs();
            }
            else {
                fournisseurs.add(fournisseurService.getFournisseurByCode(codeFournisseur));
            }
            if (fournisseurs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idFournisseur}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long idFournisseur){
        Fournisseur fournisseur = new Fournisseur();
        fournisseur= fournisseurService.getFournisseurById(idFournisseur);

        if (fournisseur == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<Fournisseur> getFournisseurByLibelle(@RequestParam(required = false) String libelleFournisseur){
//        Fournisseur fournisseur = new Fournisseur();
//        fournisseur= fournisseurService.getFournisseurByLibelle(libelleFournisseur);
//
//        if (fournisseur == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.createFournisseur(fournisseur), HttpStatus.CREATED);
    }

    @PutMapping("/{idFournisseur}")
    public ResponseEntity<Fournisseur> updateFamille(@PathVariable Long idFournisseur, @RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<>(fournisseurService.updateFournisseur(idFournisseur, fournisseur), HttpStatus.OK);
    }
}
