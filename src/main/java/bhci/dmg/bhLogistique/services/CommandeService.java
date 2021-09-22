package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.repository.CommandeRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log
@Service
public class CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<Commande> getAllCommandes(){
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long idCommande){
       return commandeRepository.findById(idCommande).orElseThrow(() ->
               new IllegalStateException(" La demande id:" + idCommande+" n'existe pas"));
    }
    
    public List<Commande> getCommandeByStatus(String codeStatut){
    	
    	return commandeRepository.findDemandesByStatus(statusRepository.findByCodeStatut(codeStatut));
    }

    public Commande getCommandeByNumber(String numeroCommande){
        return commandeRepository.findByNumeroCommande(numeroCommande).orElseThrow(() ->
                new IllegalStateException(" La demande n°:" + numeroCommande+" n'existe pas"));
    }

    public Commande updateCommande (Long idCommande, Commande commande){
        Commande commande1 = commandeRepository.findById(idCommande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idCommande+" n'existe pas"));

        commande1.setStatus(commande.getStatus());

        return commandeRepository.save(commande1);
    }

    public Commande validateCommande (Long idCommande ){
        Commande commande1 = commandeRepository.findById(idCommande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idCommande+" n'existe pas"));

        commande1.setStatus(statusRepository.findByCodeStatut("VAL"));

        return commandeRepository.save(commande1);
    }

    public Commande refuseCommande (Long idCommande){
        Commande commande1 = commandeRepository.findById(idCommande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idCommande+" n'existe pas"));

        commande1.setStatus(statusRepository.findByCodeStatut("REJ"));

        return commandeRepository.save(commande1);
    }

    public Commande createCommande(Commande commande){
        Commande newCommande = new Commande();

        log.info("-- Création d'une nouvelle commande ! -- ");
        if (commandeRepository.findByNumeroCommande(commande.getNumeroCommande()).isPresent()){
            throw  new IllegalStateException(" Cette commande a déjà été enregistrée");
        }
        if(commande == null) {
            throw  new IllegalStateException(" La commande n'a pas été renseignée ");
        }
        if(commande.getNumeroCommande() == null) {
            throw  new IllegalStateException(" Le numéro de la commande est obligatoire ");
        }
        if(commande.getDateCommande() == null) {
            commande.setDateCommande(LocalDate.now());
        }

        if(commande.getCommandeDetails() == null || commande.getCommandeDetails().isEmpty())
            throw new IllegalStateException(" la commande ne contient aucun article ");

        commande.getCommandeDetails().forEach(commDet -> commDet.setCommande(commande));
        commande.setStatus(statusRepository.findByCodeStatut("ATT"));

        return commandeRepository.save(commande);
//        return commande;
    }
}
