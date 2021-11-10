package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.dto.DemandeDto;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.services.DemandeService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/demandes")
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
                return new ResponseEntity<>(demandes,HttpStatus.OK);
            }
            return new ResponseEntity<>(demandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/statutEtDirection")
    public ResponseEntity<List<Demande>> getDemandesByStatutDemandeAndDirectionDemandeur(@RequestParam(required = false) String statut, @RequestParam(required = false) Long idDirection) {

        try {
        	List<Demande> demandes = new ArrayList<Demande>();
        	demandes = demandeService.getDemandesByStatutDemandeAndDirectionDemandeur(statut, idDirection);
        	return new ResponseEntity<>(demandes, HttpStatus.OK);
        	
        }
            catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/statusEtDirection")
    public ResponseEntity<List<Demande>> getDemandesByStatusAndDirectionId(@RequestParam(required = false) Long idStatus, @RequestParam(required = false) Long idDirection) {

        try {
        	List<Demande> demandes = new ArrayList<Demande>();
        	System.out.println("etape 1");
        	demandes = demandeService.getDemandesByStatusAndDirectionDemandeur(idStatus, idDirection);
        	return new ResponseEntity<>(demandes, HttpStatus.OK);
        	
        }
            catch (Exception e) {
            	e.printStackTrace();
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
    	log.info("----------------- create demande ----------");
        return new ResponseEntity<>(demandeService.createDemande(demande), HttpStatus.CREATED);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<DemandeDto> createDemandeV2(@RequestBody DemandeDto demandeDto) {
    	log.info("-- Création d'une demande ...");
    	DemandeDto createResponse = demandeService.createDemandeV2(demandeDto);
    	log.info("-- Création terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<DemandeDto> updateDemande(@RequestBody DemandeDto demandeDto) {
    	log.info("-- Mise à jour d'une demande ...");
    	DemandeDto updateResponse = demandeService.updateDemande(demandeDto);
    	log.info("-- Mise à jour terminée.");
        return new ResponseEntity<>(updateResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/delete")
    public ResponseEntity<DemandeDto> deleteDemande(@RequestBody DemandeDto demandeDto) {
    	log.info("-- Suppression d'une demande ...");
    	DemandeDto createResponse = demandeService.deleteDemande(demandeDto);
    	log.info("-- Suppression terminée.");
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
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
