package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.dao.DemandeArticle;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    public List<Demande> getAllDemandes(){
        return demandeRepository.findAll();
    }

    public Demande getDemandeById(Long idDemande){
       return demandeRepository.findById(idDemande).orElseThrow(() ->
               new IllegalStateException(" La demande id:" + idDemande+" n'existe pas"));
    }

    public Demande getDemandeByReference(String numRefDemande){
        return demandeRepository.findByNumRef(numRefDemande).orElseThrow(() ->
                new IllegalStateException(" La demande n°:" + numRefDemande+" n'existe pas"));
    }

    public List<Demande> getDemandeByStatutDemande(String statutDemande){
        return demandeRepository.findDemandesByStatutDemande(statutDemande);
    }

    public Demande updateDemande (Long idDemande, Demande demande){
        Demande demande1 = demandeRepository.findById(idDemande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idDemande+" n'existe pas"));

        demande1.setStatutDemande(demande.getStatutDemande());

        return demandeRepository.save(demande1);
    }

    public Demande validateDemande (Long idDemande ){
        Demande demande1 = demandeRepository.findById(idDemande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idDemande+" n'existe pas"));

        demande1.setStatutDemande(StatutDemande.VALIDEE_POUR_TRAITEMENT.getValue());

        return demandeRepository.save(demande1);
    }

    public Demande refuseDemande (Long idDemande){
        Demande demande1 = demandeRepository.findById(idDemande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idDemande+" n'existe pas"));

        demande1.setStatutDemande(StatutDemande.REJETEE.getValue());

        return demandeRepository.save(demande1);
    }

    public Demande createDemande(Demande demande){
        Demande newDemande = new Demande();
        if (demandeRepository.findByNumRef(demande.getNumRef()).isPresent()) {
            throw new IllegalStateException("Cette demande a déjà été enregistrée");
        }

        System.out.println(demande.getDemandeArticles().toString());

        newDemande.setDateDemande(demande.getDateDemande());
        newDemande.setDemandeur(demande.getDemandeur());
        newDemande.setStatutDemande(demande.getStatutDemande());
        newDemande.setEstimation(demande.getEstimation());
        newDemande.setUrgent(demande.isUrgent());
        newDemande.setJustifUrgence(demande.getJustifUrgence());
        newDemande.setNumRef(demande.getNumRef());
        newDemande.setObservation(demande.getObservation());
//        newDemande.setDemandeArticles(demande.getDemandeArticles());
        newDemande.setDemandeArticles(new ArrayList<DemandeArticle>())  ;
        for(DemandeArticle demandeArticle: demande.getDemandeArticles()){
            DemandeArticle newDemArticle = new DemandeArticle(demandeArticle.getQuantite(), demandeArticle.getDemande(), demandeArticle.getArticle());
            newDemande.addArticles(newDemArticle);
        }
//        System.out.println(newDemande.getDemandeArticles().toString());
        return demandeRepository.save(newDemande);
    }
}
