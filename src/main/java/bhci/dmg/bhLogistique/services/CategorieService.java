/**
 * 
 */
package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bhci.dmg.bhLogistique.dao.Categorie;
import bhci.dmg.bhLogistique.dto.CategorieDto;
import bhci.dmg.bhLogistique.repository.CategorieRepository;
import bhci.dmg.bhLogistique.transformer.Transformer;
import lombok.extern.java.Log;

/**
 * @author ikouame
 *
 */

@Log
@Service
public class CategorieService {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	private Transformer<CategorieDto, Categorie> transformer = new Transformer<CategorieDto, Categorie>(CategorieDto.class,
			Categorie.class);
	
	public List<CategorieDto> getAllCategorie(){
		log.info("-- get all categorie ...");
		List<CategorieDto> response = new ArrayList<CategorieDto>();
        List<Categorie> allCategorie = categorieRepository.findAll();
        if(allCategorie != null && !allCategorie.isEmpty()) 
        	response = transformer.convertToDto(allCategorie);
        log.info("-- get all categorie finish.");
		return response;
	}
	
	public CategorieDto createCategorie(CategorieDto categorieDto){
		log.info("-- create categorie begin ...");
		if( categorieDto.getCodeCategorie() == null  || categorieDto.getLibelleCategorie() == null
				|| categorieDto.getDescriptionCategorie() == null ) {
			throw new IllegalStateException("code, libelle et description sont les champs obligatoires");
		}
		
		if (categorieRepository.findByCodeCategorie( categorieDto.getCodeCategorie()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé");
		}
        Categorie entityToSave = transformer.convertToEntity(categorieDto);
        log.info("-- create categorie end.");
	    return transformer.convertToDto(categorieRepository.save(entityToSave));
	}
	
	public CategorieDto updateCategorie(CategorieDto categorieDto){
		log.info("-- update categorie begin ...");
		if( categorieDto.getIdCategorie() == null ) {
			throw new IllegalStateException("idCategorie est un champ obligatoire pour ce traitement");
		}
		
		Categorie categorieToUpdate = categorieRepository.findByIdCategorie(categorieDto.getIdCategorie().longValue());
		
		if(categorieToUpdate == null) {
			throw new IllegalStateException("Categorie inexistant");
		}
		
		if ( !categorieDto.getCodeCategorie().equals(categorieToUpdate.getCodeCategorie()) 
				&& categorieRepository.findByCodeCategorie( categorieDto.getCodeCategorie()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé ! ");
		}

		if(categorieDto.getCodeCategorie() != null)  categorieToUpdate.setCodeCategorie(categorieDto.getCodeCategorie());
		if(categorieDto.getLibelleCategorie() != null)  categorieToUpdate.setLibelleCategorie(categorieDto.getLibelleCategorie());
		if(categorieDto.getDescriptionCategorie() != null)  categorieToUpdate.setDescriptionCategorie(categorieDto.getDescriptionCategorie());
		
        Categorie entityToSave = transformer.convertToEntity(categorieDto);
        log.info("-- update categorie end.");
	    return transformer.convertToDto(categorieRepository.save(entityToSave));
	}
	
	

}
