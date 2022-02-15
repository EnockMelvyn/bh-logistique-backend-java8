package bhci.dmg.bhLogistique.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Inventaire;
import bhci.dmg.bhLogistique.dao.InventaireDetail;
import bhci.dmg.bhLogistique.dao.MouvementStock;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.enums.TypeMouvement;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import bhci.dmg.bhLogistique.repository.InventaireRepository;
import bhci.dmg.bhLogistique.repository.MouvementStockRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;

@Service
public class InventaireService {

	@Autowired
	InventaireRepository invRepo;
	
	@Autowired
	ArticleRepository artRepo;
	
	@Autowired
	MouvementStockRepository mouvStockRepo;
	
	@Autowired
	StatusRepository statusRepo;
	
	public List<Inventaire> getAllInventaire(){
		return invRepo.findAll();
	}
	
	
	// Fonction de génération des lignes de détials de l'inventaire avant renseignement des quantités comptées
	@Transactional
	public Inventaire generateInventaire(Inventaire inventaire) {
		List<Article> articles = artRepo.findAll();
		Status statutInitial = statusRepo.findByCodeStatut("ENC");
		
		Inventaire inv = new Inventaire();
		inv.setDateInventaire(inventaire.getDateInventaire());
		inv.setFamille(inventaire.getFamille());
		inv.setSousFamille(inventaire.getSousFamille());
		inv.setLibelle(inventaire.getLibelle());
		inv.setStatus(statutInitial);
		
		inv.setCreatedBy(inventaire.getCreatedBy());
		inv.setCreatedAt(LocalDateTime.now());
		
		inv.setDetails(new ArrayList<>());
		articles.forEach(article -> {
			InventaireDetail invDet =new InventaireDetail();
			invDet.setArticle(article);
			invDet.setCmup(article.getCmup());
			invDet.setQteCalcule(article.getQuantiteStock());
			
			inv.addDetail(invDet);
		});
		
		return invRepo.save(inv);
	}
	
	
	// Fonction de MAJ des lignes de détails 
	// Servira à enregistrer les quantités comptées
	public Inventaire majInventaire(Inventaire inventaire) {
		Double ecartVal = 0.0;
		for(InventaireDetail invDet : inventaire.getDetails()) {
			invDet.setInventaire(inventaire);
			ecartVal=(ecartVal + (invDet.getQteComptee().doubleValue()-invDet.getQteCalcule().doubleValue())*invDet.getCmup());
		}
		inventaire.setValeurEcart(ecartVal);
		return invRepo.save(inventaire);
	}

	// Fonction de validation de l'inventaire
	public Inventaire validerInventaire(Inventaire inventaire) {
		Double ecartVal = 0.0;
		for(InventaireDetail invDet : inventaire.getDetails()) {
			//Mise à jour des lignes de l'inventaire
			invDet.setInventaire(inventaire);
			ecartVal=(ecartVal + (invDet.getQteComptee().doubleValue()-invDet.getQteCalcule().doubleValue())*invDet.getCmup());
			
			//enregistrement des mouvements de stocks
			mouvStockRepo.save( new MouvementStock(inventaire.getDateInventaire(),invDet.getArticle(), 
					invDet.getQteCalcule(), invDet.getQteComptee(), TypeMouvement.INVENTAIRE, invDet.getCmup())
					); 
			//MAJ des quantités des articles
			invDet.getArticle().setQuantiteStock(invDet.getQteComptee());
			artRepo.save(invDet.getArticle());
		};
		inventaire.setValeurEcart(ecartVal);
		inventaire.setStatus(statusRepo.findByCodeStatut("VAL"));
		return invRepo.save(inventaire);
	}

}
