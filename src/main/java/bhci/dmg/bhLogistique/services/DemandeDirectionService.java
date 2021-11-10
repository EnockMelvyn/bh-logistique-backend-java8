package bhci.dmg.bhLogistique.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.DemandeDirection;
import bhci.dmg.bhLogistique.dao.DemandeDirectionDetails;
import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import bhci.dmg.bhLogistique.repository.DemandeArticleParDirectionRepository;
import bhci.dmg.bhLogistique.repository.DemandeDirectionDetailsRepository;
import bhci.dmg.bhLogistique.repository.DemandeDirectionRepository;
import bhci.dmg.bhLogistique.repository.DemandeRepository;
import bhci.dmg.bhLogistique.repository.DirectionRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;

@Service
public class DemandeDirectionService {

	@Autowired
	DemandeDirectionRepository demandeDirectionRepository;
	
	@Autowired
	DirectionRepository directionRepository;
	
	@Autowired
	DemandeRepository demandeRepository;
		
	@Autowired
	DemandeArticleParDirectionRepository demArticleParDirRepo;
	
	@Autowired
	DemandeDirectionDetailsRepository demDirDetRepo;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	public List<DemandeDirection> getAllDemandeDirection(){
		return demandeDirectionRepository.findAll();
	}
	
	public List<DemandeDirection> getDemandeDirectionByCodeStatus(String codeStatus){
		
		return demandeDirectionRepository.findByStatus(statusRepository.findByCodeStatut(codeStatus));
	}
	
	public DemandeDirection generateDemandeDirection(Long idDirection) {
		Direction direction = directionRepository.findById(idDirection).get(); // Gerer l'exception
		Status status = statusRepository.findByCodeStatut("ATT");
		//DemandeDirection demandeDirectionToSave= new DemandeDirection();
		
		DemandeDirection demandeDirection =demandeDirectionRepository.findByDirectionAndStatus(direction, status).orElse(new DemandeDirection());
		
		if (demandeDirection.getId() != null) {
			demandeDirection.setModifiedAt(LocalDateTime.now());
			// remplissage du tableau des articles demandés par direction
			demArticleParDirRepo.findByIdDirectionAndIdStatus(idDirection, status.getIdStatut()).forEach(demArt -> {
				Article article = articleRepository.findById(demArt.getIdArticle()).get();
				DemandeDirectionDetails demDirDet = demDirDetRepo.findByArticle(article).orElse( new DemandeDirectionDetails());
				if (demDirDet.getId() !=null) {
					demDirDet.setModifiedAt(LocalDateTime.now());
					demDirDet.setQuantiteDemande(demDirDet.getQuantiteDemande()+demArt.getQuantite());
				} else {
					demDirDet.setCreatedAt(LocalDateTime.now());
					demDirDet.setArticle(article);
					demDirDet.setQuantiteDemande(demArt.getQuantite());
					demandeDirection.addDetail(demDirDet);
				}
				
			});
			
			//mise à jour des demandes en attente de la direction
			demandeRepository.findDemandesByStatusAndDirectionId(status, direction).forEach(demande -> {
				System.out.println("MAJ des demandes en cours");
				demande.setDemandeDirection(demandeDirection);
				demande.setStatus(statusRepository.findByCodeStatut("DIR"));
			});
			
			//demandeDirectionToSave = demandeDirection;
			//return demandeDirectionRepository.save(demandeDirection);
			
		} else {
			demandeDirection.setDateDemande(LocalDateTime.now());
			demandeDirection.setStatus(status);
			demandeDirection.setDirection(direction);
			demandeDirection.setCreatedAt(LocalDateTime.now());
			
			System.out.println("Enregistrement en cours");
			
			// remplissage du tableau des articles demandés par direction
			demArticleParDirRepo.findByIdDirectionAndIdStatus(idDirection, status.getIdStatut()).forEach(demArt -> {
				System.out.println(demArt);
				DemandeDirectionDetails demDirDet = new DemandeDirectionDetails();
				demDirDet.setCreatedAt(LocalDateTime.now());
				demDirDet.setArticle(articleRepository.findById(demArt.getIdArticle()).get());
				demDirDet.setQuantiteDemande(demArt.getQuantite());
				
				List<DemandeDirectionDetails> tableau = new ArrayList<>();
				demandeDirection.setDemandeDirectionDetails(tableau);
				demandeDirection.addDetail(demDirDet);
				//System.out.println(demandeDirection.toString());
				});
				
				if ( demandeDirection.getDemandeDirectionDetails()==null || demandeDirection.getDemandeDirectionDetails().size() ==0) {
					throw new IllegalStateException(" Aucune nouvelle demande enregistrée pour la direction "+ direction.getLibelleDirection());
				}
				
				//mise à jour des demandes en attente de la direction
				demandeRepository.findDemandesByStatusAndDirectionId(status, direction).forEach(demande -> {
					System.out.println("MAJ des demandes en cours");
					demande.setDemandeDirection(demandeDirection);
					demande.setStatus(statusRepository.findByCodeStatut("DIR"));
				});
			
		}
				
		return demandeDirectionRepository.save(demandeDirection);
	}
	
	
	public DemandeDirection sendToDmg(DemandeDirection demandeDirection) {
		//DemandeDirection demDirToSave = demandeDirectionRepository.findById(demandeDirection.getId()).get();
		
		demandeDirection.getDemandeDirectionDetails().forEach(element -> {
			element.setModifiedAt(LocalDateTime.now());
			element.setDemandeDirection(demandeDirection);
		});
		Status statusDMG = statusRepository.findByCodeStatut("DMG");
		demDirDetRepo.saveAll(demandeDirection.getDemandeDirectionDetails());
		demandeDirection.setStatus(statusDMG);
		demandeRepository.findDemandesByDemandeDirection(demandeDirection).forEach(dem -> {
			dem.setStatus(statusDMG);
		});
		demandeDirection.setModifiedAt(LocalDateTime.now());
		return demandeDirectionRepository.save(demandeDirection);
		
	}
	
	public DemandeDirection validationDmg(DemandeDirection demandeDirection) {
		//DemandeDirection demDirToSave = demandeDirectionRepository.findById(demandeDirection.getId()).get();
		
		demandeDirection.getDemandeDirectionDetails().forEach(element -> {
			element.setModifiedAt(LocalDateTime.now());
			element.setDemandeDirection(demandeDirection);
			/*demDirToSave.getDemandeDirectionDetails().forEach( elm2 -> {
				if(elm2.getId()== element.getId() && element.getQuantiteValideDir()>=0) {
					elm2.setQuantiteValideDir(element.getQuantiteValideDir());
				}
			});*/
				
		});
		
		Status statusVAL = statusRepository.findByCodeStatut("VAL");
		demDirDetRepo.saveAll(demandeDirection.getDemandeDirectionDetails());
		demandeDirection.setStatus(statusVAL);
		demandeRepository.findDemandesByDemandeDirection(demandeDirection).forEach(dem -> {
			dem.setStatus(statusVAL);
		});
		demandeDirection.setModifiedAt(LocalDateTime.now());
		return demandeDirectionRepository.save(demandeDirection);
		
	}
	
}
