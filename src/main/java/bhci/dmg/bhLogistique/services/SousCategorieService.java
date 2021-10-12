/**
 * 
 */
package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Categorie;
import bhci.dmg.bhLogistique.dao.SousCategorie;
import bhci.dmg.bhLogistique.dto.SousCategorieDto;
import bhci.dmg.bhLogistique.repository.CategorieRepository;
import bhci.dmg.bhLogistique.repository.SousCategorieRepository;
import bhci.dmg.bhLogistique.transformer.Transformer;
import lombok.extern.java.Log;

/**
 * @author ikouame
 *
 */

@Log
@Service
public class SousCategorieService {
	
	@Autowired
	private SousCategorieRepository sousCategorieRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	
	private Transformer<SousCategorieDto, SousCategorie> transformer = new Transformer<SousCategorieDto, SousCategorie>(SousCategorieDto.class,
			SousCategorie.class);
	
	public List<SousCategorieDto> getAllSousCategorie(){
		log.info("-- get all sousCategorie ...");
		List<SousCategorieDto> response = new ArrayList<SousCategorieDto>();
        List<SousCategorie> allSousCategorie = sousCategorieRepository.findAll();
        if(allSousCategorie != null && !allSousCategorie.isEmpty()) 
        	response = transformer.convertToDto(allSousCategorie);
        log.info("-- get all sousCategorie finish.");
		return response;
	}
	
	public List<SousCategorieDto> getSousCategorieByCategorie(Integer idCategorie){
		log.info("-- get sousCategorie by categorie ...");
		List<SousCategorieDto> response = new ArrayList<SousCategorieDto>();
		Categorie categorie = null;
		if(idCategorie != null) categorie = categorieRepository.findByIdCategorie(idCategorie.longValue());
		if(categorie == null) throw new IllegalStateException("la categorie fournie n'existe pas dans le système");
		
        List<SousCategorie> allSousCategorie = sousCategorieRepository.findByCategorie(categorie);
        if(allSousCategorie != null && !allSousCategorie.isEmpty()) 
        	response = transformer.convertToDto(allSousCategorie);
        log.info("-- get sousCategorie by categorie finish.");
		return response;
	}
	
	public SousCategorieDto createSousCategorie(SousCategorieDto sousCategorieDto){
		log.info("-- create sousCategorie begin ...");
		if( sousCategorieDto.getCodeSousCategorie() == null  || sousCategorieDto.getLibelleSousCategorie() == null
				|| sousCategorieDto.getDescriptionSousCategorie() == null ) {
			throw new IllegalStateException("code, libelle et description sont les champs obligatoires");
		}
		
		if (sousCategorieRepository.findByCodeSousCategorie( sousCategorieDto.getCodeSousCategorie()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé");
		}
        SousCategorie entityToSave = transformer.convertToEntity(sousCategorieDto);
        log.info("-- create sousCategorie end.");
	    return transformer.convertToDto(sousCategorieRepository.save(entityToSave));
	}
	
	public SousCategorieDto updateSousCategorie(SousCategorieDto sousCategorieDto){
		log.info("-- update sousCategorie begin ...");
		if( sousCategorieDto.getIdSousCategorie() == null ) {
			throw new IllegalStateException("idSousCategorie est un champ obligatoire pour ce traitement");
		}
		
		SousCategorie sousCategorieToUpdate = sousCategorieRepository.findByIdSousCategorie(sousCategorieDto.getIdSousCategorie().longValue());
		
		if(sousCategorieToUpdate == null) {
			throw new IllegalStateException("SousCategorie inexistant");
		}
		
		if ( !sousCategorieDto.getCodeSousCategorie().equals(sousCategorieToUpdate.getCodeSousCategorie()) 
				&& sousCategorieRepository.findByCodeSousCategorie( sousCategorieDto.getCodeSousCategorie()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé ! ");
		}

		if(sousCategorieDto.getCodeSousCategorie() != null)  sousCategorieToUpdate.setCodeSousCategorie(sousCategorieDto.getCodeSousCategorie());
		if(sousCategorieDto.getLibelleSousCategorie() != null)  sousCategorieToUpdate.setLibelleSousCategorie(sousCategorieDto.getLibelleSousCategorie());
		if(sousCategorieDto.getDescriptionSousCategorie() != null)  sousCategorieToUpdate.setDescriptionSousCategorie(sousCategorieDto.getDescriptionSousCategorie());
		
        SousCategorie entityToSave = transformer.convertToEntity(sousCategorieDto);
        log.info("-- update sousCategorie end.");
	    return transformer.convertToDto(sousCategorieRepository.save(entityToSave));
	}
	
	

}
