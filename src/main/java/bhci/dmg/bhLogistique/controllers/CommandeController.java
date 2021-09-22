package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.services.CommandeService;
import bhci.dmg.bhLogistique.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes(@RequestParam(required = false) String numeroCommande) {

        try {
            List<Commande> commandes = new ArrayList<Commande>();
            if (numeroCommande == null) {
                commandes = commandeService.getAllCommandes();
            }
            else {
                commandes.add(commandeService.getCommandeByNumber(numeroCommande));
            }
            if (commandes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/statut")
    public ResponseEntity<List<Commande>> getCommandesByStatut(@RequestParam(required = false) String statutCommande) {

        try {
        	System.out.println(statutCommande);
            List<Commande> commandes = new ArrayList<Commande>();
            if (statutCommande == null) {
                commandes = commandeService.getAllCommandes();
            }
            else {
            	commandes = commandeService.getCommandeByStatus(statutCommande);
            }
            if (commandes.isEmpty()) {
                return new ResponseEntity<>(commandes,HttpStatus.OK);
            }
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long idCommande){
        Commande commande = new Commande();
        commande = commandeService.getCommandeById(idCommande);

        if (commande == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        return new ResponseEntity<>(commandeService.createCommande(commande), HttpStatus.CREATED);
    }

    @PutMapping("/validate")
    public ResponseEntity<Commande> validateCommande(@RequestParam("idCommande") Long idCommande) {
        return new ResponseEntity<>(commandeService.validateCommande(idCommande), HttpStatus.OK);
    }

    @PutMapping("/refuse")
    public ResponseEntity<Commande> refuseDemande(@RequestParam("idCommande") Long idCommande) {
        return new ResponseEntity<>(commandeService.refuseCommande(idCommande), HttpStatus.OK);
    }
/*
    @PutMapping("/{idDemande}")
    public ResponseEntity<Commande> updateDemande(@PathVariable Long idDemande, @RequestBody Commande demande) {
        return new ResponseEntity<>(commandeService.updateCommande(idDemande, demande), HttpStatus.OK);
    }



    */
}
