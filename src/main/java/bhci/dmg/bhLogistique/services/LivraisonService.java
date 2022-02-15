package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Livraison;
import bhci.dmg.bhLogistique.dao.LivraisonDetail;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.enums.TypeMouvement;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import bhci.dmg.bhLogistique.repository.LivraisonRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LivraisonService {

    @Autowired
    LivraisonRepository livraisonRepository;
    
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MouvementStockService mouvementStockService;
    
    @Autowired
    StatusRepository statusRepository;
    
    @Autowired
    CommandeService commandeService;

    public List<Livraison> getAllLivraisons() {
        /*List<Livraison> livraisons= new ArrayList<Livraison>();
        livraisonRepository.findAll().forEach(livraisons::add);*/
        return livraisonRepository.findAll();
    }

    public Livraison getLivraisonById(Long idLivraison){
        return  livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );
    }

    public Livraison getLivraisonByNumeroBl(String numBl){
        return  livraisonRepository.findByNumeroBl(numBl).orElseThrow(() ->
                new IllegalStateException(" Le n° de BL:" + numBl +" n'existe pas")
        );
    }

    public Livraison deleteLivraisonById(Long idLivraison){
        return  livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );
    }

    public Livraison updateLivraison(Long idLivraison, Livraison livraison){
        Livraison livraisonToUpdate = livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );


        livraisonToUpdate.setFournisseur(livraison.getFournisseur());
        livraisonToUpdate.setDateLivraison(livraison.getDateLivraison());
        livraisonToUpdate.setNumeroBl(livraison.getNumeroBl());

        return livraisonRepository.save(livraisonToUpdate);
    }

    public Livraison createLivraison(Livraison livraison) {
        boolean livraisonExists = livraisonRepository.findByNumeroBl(livraison.getNumeroBl()).isPresent();

        if (livraisonExists) {
            throw new IllegalStateException("Cette livraison a déjà été enregistrée");
        }

        Livraison livraison1 = new Livraison();

        livraison1.setDateLivraison(livraison.getDateLivraison());
        livraison1.setNumeroBl(livraison.getNumeroBl());
        livraison1.setFournisseur(livraison.getFournisseur());
        livraison1.setCommande(livraison.getCommande());
        livraison1.setLivraisonDetails(new ArrayList<LivraisonDetail>());
        for(LivraisonDetail livraisonDetail: livraison.getLivraisonDetails()){
            LivraisonDetail newLivDet = new LivraisonDetail();
            newLivDet.setLivraison(livraisonDetail.getLivraison());
            newLivDet.setArticle(livraisonDetail.getArticle());
            newLivDet.setQuantite(livraisonDetail.getQuantite());
            newLivDet.setPrixUnitaire(livraisonDetail.getPrixUnitaire());

            livraison1.addLivraisonDetail(newLivDet);

            mouvementStockService.createMouvementStock(Arrays.asList(
                    new MouvementStock(
                            livraison.getDateLivraison(),
                            livraisonDetail.getArticle(),
                            livraisonDetail.getArticle().getQuantiteStock(),
                            livraisonDetail.getQuantite(),
                            TypeMouvement.ENTREE,
                            livraisonDetail.getPrixUnitaire()
                    )
                )
            		
    		);
            
            //maj de l'article
           // Article article = articleRepository.getById(livraisonDetail.getArticle().getIdArticle());
            Article article = livraisonDetail.getArticle();
            int qteFinale = article.getQuantiteStock()+livraisonDetail.getQuantite();
            double newCmup = ((article.getCmup()*article.getQuantiteStock()) + (livraisonDetail.getQuantite()*livraisonDetail.getPrixUnitaire()))/ qteFinale;
            
            article.setCmup(newCmup);article.setQuantiteStock(qteFinale);
            articleRepository.save(article);
            
            //maj de la commande
            // Commande commande = commandeService.getCommandeById(livraison1.getCommande().getIdCommande());
            Commande commande = livraison1.getCommande();
            System.out.println("-------------------------------------------------");
            System.out.println(livraison1.getCommande().isLivraisonTotal());
            System.out.println("-------------------------------------------------");            
            if (livraison1.getCommande().isLivraisonTotal()) {
            	commande.setStatus(statusRepository.findByCodeStatut("LIV"));
            }
            
            
            commandeService.updateCommande(commande.getIdCommande(), commande);
        }


        return livraisonRepository.save(livraison1);
    }
}
