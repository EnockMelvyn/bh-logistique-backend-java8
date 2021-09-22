package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.*;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.enums.TypeMouvement;
import bhci.dmg.bhLogistique.repository.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SortieService {


    @Autowired
    SortieRepository sortieRepository;
    @Autowired
    ArticleService articleService;
    @Autowired
    DemandeService demandeService;
    @Autowired
    MouvementStockService mouvementStockService;

    public List<Sortie> getAllSorties() {
        /*List<Sortie> sorties= new ArrayList<Sortie>();
        sortieRepository.findAll().forEach(sorties::add);*/
        return sortieRepository.findAll();
    }

    public Sortie getSortieById(Long idSortie){
        return  sortieRepository.findById(idSortie).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSortie +" n'existe pas")
        );
    }

    public Sortie getSortieByReference(String reference){
        return  sortieRepository.findByReference(reference).orElseThrow(() ->
                new IllegalStateException(" La reference:" + reference +" n'existe pas")
        );
    }

    public Sortie deleteSortieById(Long idSortie){
        return  sortieRepository.findById(idSortie).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSortie +" n'existe pas")
        );
    }


    public Sortie createSortie(Sortie sortie) {

        if (sortieRepository.findByReference(sortie.getReference()).isPresent()) {
            throw new IllegalStateException("Cette sortie a déjà été utilisé");
        }

        // création et insertion en BD du mouvement de stock
        mouvementStockService.createMouvementStock(Arrays.asList(
               new MouvementStock(
                       sortie.getDateSortie(),
                       sortie.getArticle(),
                       sortie.getArticle().getQuantiteStock(),
                       sortie.getQuantite(),
                       TypeMouvement.SORTIE,
                       sortie.getArticle().getCmup()
               )
        ));

        // MAJ de stock article dans la BD
        Article articleUp = sortie.getArticle();
        articleUp.setQuantiteStock(sortie.getArticle().getQuantiteStock()+sortie.getQuantite());
        articleService.updateArticle(articleUp.getIdArticle(), articleUp);

        // MAJ du statut de la demande
        Demande demandeUp = sortie.getDemande();
        demandeUp.setStatutDemande(StatutDemande.VISA_DEMANDEUR.getValue());
        demandeService.updateDemande(demandeUp.getIdDemande(), demandeUp);
        return sortieRepository.save(sortie);
    }
}
