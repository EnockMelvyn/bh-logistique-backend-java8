package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Categorie;
import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.dao.DemandeArticle;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.dao.Type;
import bhci.dmg.bhLogistique.dto.DemandeArticleDto;
import bhci.dmg.bhLogistique.dto.DemandeDto;
import bhci.dmg.bhLogistique.enums.CodeStatut;
import bhci.dmg.bhLogistique.enums.StatutDemande;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import bhci.dmg.bhLogistique.repository.CategorieRepository;
import bhci.dmg.bhLogistique.repository.DemandeArticleRepository;
import bhci.dmg.bhLogistique.repository.DemandeRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;
import bhci.dmg.bhLogistique.repository.TypeRepository;
import bhci.dmg.bhLogistique.transformer.Transformer;
import bhci.dmg.bhLogistique.utils.Utilities;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class DemandeService {
	@Value("${notification.server.backend.url}")
	private String notifServerUrl ;
    
	@Value("${notification.server.backend.content}")
	private String notifServerContent ;
	
	@Value("${notification.server.backend.subject}")
	private String notifServerSubject ;
	
	@Value("${notification.server.backend.from}")
	private String notifServerFrom ;
	
	@Autowired
	private DemandeRepository demandeRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private DemandeArticleRepository demandeArticleRepository;

	private Transformer<DemandeDto, Demande> transformer = new Transformer<DemandeDto, Demande>(DemandeDto.class,
			Demande.class);

	public List<Demande> getAllDemandes() {
		return demandeRepository.findAll();
	}

	public Demande getDemandeById(Long idDemande) {
		return demandeRepository.findById(idDemande)
				.orElseThrow(() -> new IllegalStateException(" La demande id:" + idDemande + " n'existe pas"));
	}

	public Demande getDemandeByReference(String numRefDemande) {
		return demandeRepository.findByNumRef(numRefDemande)
				.orElseThrow(() -> new IllegalStateException(" La demande n°:" + numRefDemande + " n'existe pas"));
	}

	public List<Demande> getDemandeByStatutDemande(String statutDemande) {
		return demandeRepository.findDemandesByStatutDemande(statutDemande);
	}

	public Demande updateDemande(Long idDemande, Demande demande) {
		Demande demande1 = demandeRepository.findById(idDemande)
				.orElseThrow(() -> new IllegalStateException(" L'id famille:" + idDemande + " n'existe pas"));

		demande1.setStatutDemande(demande.getStatutDemande());

		return demandeRepository.save(demande1);
	}

	public Demande validateDemande(Long idDemande) {
		Demande demande1 = demandeRepository.findById(idDemande)
				.orElseThrow(() -> new IllegalStateException(" L'id famille:" + idDemande + " n'existe pas"));

		demande1.setStatutDemande(StatutDemande.VALIDEE_POUR_TRAITEMENT.getValue());

		return demandeRepository.save(demande1);
	}

	public Demande refuseDemande(Long idDemande) {
		Demande demande1 = demandeRepository.findById(idDemande)
				.orElseThrow(() -> new IllegalStateException(" L'id famille:" + idDemande + " n'existe pas"));

		demande1.setStatutDemande(StatutDemande.REJETEE.getValue());

		return demandeRepository.save(demande1);
	}

	public Demande createDemande(Demande demande) {
		Demande newDemande = new Demande();

		if (demande == null) {
			throw new IllegalStateException("Demande non renseignée");
		}

		if (demandeRepository.findByNumRef(demande.getNumRef()).isPresent()) {
			throw new IllegalStateException("Cette demande a déjà été enregistrée");
		}

		// System.out.println(demande.getDemandeArticles().toString());
		newDemande.setDateDemande(demande.getDateDemande());
		newDemande.setDemandeur(demande.getDemandeur());
		newDemande.setStatutDemande(demande.getStatutDemande());
		newDemande.setEstimation(demande.getEstimation());
		newDemande.setUrgent(demande.isUrgent());
		newDemande.setJustifUrgence(demande.getJustifUrgence());
		newDemande.setNumRef(demande.getNumRef());
		newDemande.setObservation(demande.getObservation());
//        newDemande.setDemandeArticles(demande.getDemandeArticles());
		newDemande.setDemandeArticles(new ArrayList<DemandeArticle>());
		for (DemandeArticle demandeArticle : demande.getDemandeArticles()) {
			DemandeArticle newDemArticle = new DemandeArticle(demandeArticle.getQuantite(), demandeArticle.getDemande(),
					demandeArticle.getArticle());
			newDemande.addArticles(newDemArticle);
		}
//        System.out.println(newDemande.getDemandeArticles().toString());*/

		return demandeRepository.save(demande);
	}

	@Transactional
	public DemandeDto createDemandeV2(DemandeDto demandeDto) {
		// TODO Auto-generated method stub
		Demande demandeToSave = new Demande();
		if (demandeDto == null) {
			throw new IllegalStateException("Demande non renseignée");
		}

		if (demandeDto.getNumRef() != null && demandeRepository.findByNumRefAndIsDeleted(demandeDto.getNumRef(), Boolean.FALSE).isPresent()) {
			throw new IllegalStateException("Cette demande a déjà été enregistrée");
		}

		List<DemandeArticleDto> demandeArticlesDto = demandeDto.getDemandeArticles();
		demandeDto.setDemandeArticles(null);
		demandeToSave = transformer.convertToEntity(demandeDto);

		if (demandeDto.getIdType() != null) {
			Type exitedType = typeRepository.findByIdType(demandeDto.getIdType().longValue());
			if (exitedType != null) {
				demandeToSave.setTypeDemande(exitedType);
			} else {
				// type par défaut s'il y a lieu
			}
		}

		// renseignement du statut si renseigné
		if (demandeDto.getIdStatus() != null) {
			Status exitedStatus = statusRepository.findByIdStatut(demandeDto.getIdStatus().longValue());
			if (exitedStatus != null) {
				demandeToSave.setStatus(exitedStatus);
			} else {
				// STATUS par défaut s'il y a lieu
				exitedStatus = statusRepository.findByCodeStatut(CodeStatut.EN_ATTENTE.getValue());
				demandeToSave.setStatus(exitedStatus);
			}
		} else {
			// STATUS par défaut s'il y a lieu
			Status exitedStatus = statusRepository.findByCodeStatut(CodeStatut.EN_ATTENTE.getValue());
			System.out.println(exitedStatus);
			demandeToSave.setStatus(exitedStatus);
		}

		// renseignement du statut si renseigné
		if (demandeDto.getIdCategorie() != null) {
			Categorie exitedCategorie = categorieRepository.findByIdCategorie(demandeDto.getIdCategorie().longValue());
			if (exitedCategorie != null) {
				demandeToSave.setCategorieDemande(exitedCategorie);
			} else {
				// Categorie par défaut si possible
			}
		}
		demandeToSave.setDateDemande(LocalDateTime.now());
		demandeToSave.setCreatedAt(LocalDateTime.now());
		demandeToSave.setIsDeleted(Boolean.FALSE);
		Demande demandeSaved = demandeRepository.save(demandeToSave);

		if (demandeSaved == null) {
			throw new IllegalStateException("Demande non sauvegardée");
		}

		if (demandeArticlesDto != null && !demandeArticlesDto.isEmpty()) {
			List<DemandeArticle> demandeArticles = new ArrayList<DemandeArticle>();
			demandeArticlesDto.forEach(_demandeArticleDto -> {
				Article currentArticle = _demandeArticleDto.getIdArticle() != null
						? articleRepository.findByIdArticle(_demandeArticleDto.getIdArticle().longValue())
						: null;
				if (currentArticle != null) {
					demandeArticles
							.add(new DemandeArticle(_demandeArticleDto.getQuantite(), demandeSaved, currentArticle));
				}
			});
			if (!demandeArticles.isEmpty())
				demandeArticleRepository.saveAll(demandeArticles);

		}
		demandeDto.setDemandeArticles(demandeArticlesDto);
		demandeSaved.setDemandeArticles(null);

		DemandeDto response = transformer.convertToDto(demandeSaved);
		response.setDemandeArticles(demandeArticlesDto);
		
		/*String contentMail = "{\r\n   "
				+ " \"to\": \""+demandeDto.getDemandeur()+"\",\r\n    "
				+ "\"subject\": \""+notifServerSubject+"\",\r\n    "
				+ "\"from\": \"serviceED@bhci.ci\",\r\n    "
				+ "\"content\": \""+notifServerContent+"\"\r\n}";*/
		
		Utilities.notificationMail(notifServerUrl, demandeDto.getDemandeur(), notifServerSubject, notifServerFrom, notifServerContent);
		return response;
	}

	@Transactional
	public DemandeDto updateDemande(DemandeDto demandeDto) {
		// TODO Auto-generated method stub
		log.info("-- update begin ...");
		if (demandeDto == null || demandeDto.getIdDemande() == null) {
			throw new IllegalStateException("idDemande est un champ obligatoire pour ce traitement");
		}

		Demande demandeToUpdate = demandeRepository.findByIdDemandeAndIsDeleted(demandeDto.getIdDemande().longValue(),
				Boolean.FALSE);

		if (demandeToUpdate == null) {
			throw new IllegalStateException("Cette demande n'existe pas dans le système");
		}

		List<DemandeArticleDto> demandeArticlesDto = demandeDto.getDemandeArticles();

		// mise à jour du type si renseigné
		if (demandeDto.getIdType() != null) {
			Type exitedType = typeRepository.findByIdType(demandeDto.getIdType().longValue());
			if (exitedType != null) {
				demandeToUpdate.setTypeDemande(exitedType);
			}
		}

		// mise à jour du statut si renseigné
		if (demandeDto.getIdStatus() != null) {
			Status exitedStatus = statusRepository.findByIdStatut(demandeDto.getIdStatus().longValue());
			if (exitedStatus != null) {
				demandeToUpdate.setStatus(exitedStatus);
			}
		}

		// mise à jour de la categorie si renseigné
		if (demandeDto.getIdCategorie() != null) {
			Categorie exitedCategorie = categorieRepository.findByIdCategorie(demandeDto.getIdCategorie().longValue());
			if (exitedCategorie != null) {
				demandeToUpdate.setCategorieDemande(exitedCategorie);
			} else {
				// Categorie par défaut si possible
			}
		}

		if (demandeDto.getIsDeleted() != null) {
			demandeToUpdate.setIsDeleted(demandeDto.getIsDeleted());
		}

		if (demandeDto.getUrgent() != null) {
			demandeToUpdate.setUrgent(demandeDto.getUrgent());
		}

		if (demandeDto.getCreatedBy() != null) {
			demandeToUpdate.setCreatedBy(demandeDto.getCreatedBy());
		}

		if (demandeDto.getCreatedAt() != null) {
			demandeToUpdate.setCreatedAt(demandeDto.getCreatedAt());
		}

		if (demandeDto.getDateDemande() != null) {
			demandeToUpdate.setDateDemande(demandeDto.getDateDemande());
		}

		if (demandeDto.getDemandeur() != null) {
			demandeToUpdate.setDemandeur(demandeDto.getDemandeur());
		}

		if (demandeDto.getEstimation() != null) {
			demandeToUpdate.setEstimation(demandeDto.getEstimation());
		}

		if (demandeDto.getJustifUrgence() != null) {
			demandeToUpdate.setJustifUrgence(demandeDto.getJustifUrgence());
		}
		if (demandeDto.getModifiedBy() != null) {
			demandeToUpdate.setModifiedBy(demandeDto.getModifiedBy());
		}
		if (demandeDto.getMotifRejet() != null) {
			demandeToUpdate.setMotifRejet(demandeDto.getMotifRejet());
		}

		if (demandeDto.getObservation() != null) {
			demandeToUpdate.setObservation(demandeDto.getObservation());
		}

		if (demandeDto.getObservation() != null) {
			demandeToUpdate.setObservation(demandeDto.getObservation());
		}

		demandeToUpdate.setModifiedAt(LocalDateTime.now());
		Demande demandeSaved = demandeRepository.save(demandeToUpdate);

		if (demandeSaved == null) {
			throw new IllegalStateException("Demande non sauvegardée");
		}

		if (demandeArticlesDto != null && !demandeArticlesDto.isEmpty()) {

			List<DemandeArticle> demandeArticles = new ArrayList<DemandeArticle>();
			demandeArticlesDto.forEach(_demandeArticleDto -> {
				Article currentArticle = _demandeArticleDto.getIdArticle() != null
						? articleRepository.findByIdArticle(_demandeArticleDto.getIdArticle().longValue())
						: null;
				if (currentArticle != null) {
					demandeArticles
							.add(new DemandeArticle(_demandeArticleDto.getQuantite(), demandeSaved, currentArticle));
				}
			});
			if (!demandeArticles.isEmpty()) {
				log.info("--> Mise à jour des demandes articles ...");
				removeOldDemandeArticlesFromThisObject(demandeSaved);
				demandeArticleRepository.saveAll(demandeArticles);
				log.info("--> Mise à jour des demandes articles terminée.");
			}
		}
		log.info("-- update end ...");
		demandeSaved.setDemandeArticles(null);
		DemandeDto response = transformer.convertToDto(demandeSaved);
		response.setDemandeArticles(demandeArticlesDto);
		//Utilities.notificationMail(notifServerUrl, notifServerContent);
		return response;
	}

	private void removeOldDemandeArticlesFromThisObject(Demande demandeSaved) {
		// TODO Auto-generated method stub
		log.info("---> suppression des demanadeArticles ...");
		List<DemandeArticle> oldDemandeArticles = demandeArticleRepository.findByDemandeAndIsDeleted(demandeSaved,
				Boolean.FALSE);
		oldDemandeArticles.stream().forEach(_oldDemadeArticle -> {
			_oldDemadeArticle.setIsDeleted(Boolean.TRUE);
			_oldDemadeArticle.setModifiedAt(LocalDateTime.now());
		});
		demandeArticleRepository.saveAll(oldDemandeArticles);
		log.info("---> suppression des demanadeArticles terminée.");
	}

	@Transactional
	public DemandeDto deleteDemande(DemandeDto demandeDto) {
		// TODO Auto-generated method stub
		log.info("-- delete begin ...");
		if (demandeDto == null || demandeDto.getIdDemande() == null) {
			throw new IllegalStateException("idDemande est un champ obligatoire pour ce traitement");
		}
		Demande demandeToDelete = demandeRepository.findByIdDemandeAndIsDeleted(demandeDto.getIdDemande().longValue(),
				Boolean.FALSE);

		if (demandeToDelete == null) {
			throw new IllegalStateException("Cette demande n'existe pas dans le système");
		}
		
		demandeToDelete.setIsDeleted(Boolean.TRUE);
		demandeToDelete.setModifiedAt(LocalDateTime.now());
		Demande demandeDeleted= demandeRepository.save(demandeToDelete);
		removeOldDemandeArticlesFromThisObject(demandeDeleted);
		log.info("-- delete end ...");
		DemandeDto demandeDeletedDto = new DemandeDto();
		demandeDeletedDto.setIsDeleted(Boolean.TRUE);
		
		return demandeDeletedDto;
	}
}
