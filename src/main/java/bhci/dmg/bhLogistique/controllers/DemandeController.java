package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    DemandeService demandeService;

    @GetMapping
    public ResponseEntity<List<Demande>> getAllDemandes(@RequestParam(required = false) String numRefDemande) {

        try {
            List<Demande> demandes = new ArrayList<Demande>();
            if (numRefDemande == null) {
                demandes = demandeService.getAllDemandes();
            }
            else {
                    demandes.add(demandeService.getDemandeByReference(numRefDemande));
            }
            if (demandes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(demandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/statut")
    public ResponseEntity<List<Demande>> getDemandesByStatutDemande(@RequestParam(required = false) String code) {


        System.out.println("---------------------------------");
    	System.out.println(code);
    	System.out.println(StatutDemande.EN_ATTENTE.getValue());
        try {
            System.out.println("Entrée");
            List<Demande> demandes = new ArrayList<Demande>();
            if (code == null) {
                demandes = demandeService.getAllDemandes();
            }
            else if(code!=null) {
            	demandes = demandeService.getDemandeByStatutDemande(code);
            }
            
            if (demandes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(demandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idDemande}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long idDemande){
        Demande demande = new Demande();
        demande= demandeService.getDemandeById(idDemande);

        if (demande == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(demande, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande) {
        return new ResponseEntity<>(demandeService.createDemande(demande), HttpStatus.CREATED);
    }

    @PutMapping("/{idDemande}")
    public ResponseEntity<Demande> updateDemande(@PathVariable Long idDemande, @RequestBody Demande demande) {
        return new ResponseEntity<>(demandeService.updateDemande(idDemande, demande), HttpStatus.OK);
    }

    @PutMapping("/validate/{idDemande}")
    public ResponseEntity<Demande> validateOrRefuseDemande(@PathVariable Long idDemande) {
        return new ResponseEntity<>(demandeService.validateDemande(idDemande), HttpStatus.OK);
    }

    @PutMapping("/refuse/{idDemande}")
    public ResponseEntity<Demande> refuseDemande(@PathVariable Long idDemande) {
        return new ResponseEntity<>(demandeService.refuseDemande(idDemande), HttpStatus.OK);
    }
}
