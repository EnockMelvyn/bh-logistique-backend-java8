/**
 * 
 */
package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bhci.dmg.bhLogistique.dao.Type;
import bhci.dmg.bhLogistique.dto.TypeDto;
import bhci.dmg.bhLogistique.repository.TypeRepository;
import bhci.dmg.bhLogistique.transformer.Transformer;
import lombok.extern.java.Log;

/**
 * @author ikouame
 *
 */

@Log
@Service
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;
	
	private Transformer<TypeDto, Type> transformer = new Transformer<TypeDto, Type>(TypeDto.class,
			Type.class);
	
	
	public List<TypeDto> getAllType(){
		log.info("-- get all type ...");
		List<TypeDto> response = new ArrayList<TypeDto>();
        List<Type> allType = typeRepository.findAll();
        if(allType != null && !allType.isEmpty()) 
        	response = transformer.convertToDto(allType);
        log.info("-- get all type finish.");
		return response;
	}
	
	
	public TypeDto createType(TypeDto typeDto){
		log.info("-- create type begin ...");
		if( typeDto.getCodeType() == null  || typeDto.getLibelleType() == null
				|| typeDto.getDescriptionType() == null ) {
			throw new IllegalStateException("code, libelle et description sont les champs obligatoires");
		}
		
		if (typeRepository.findByCodeType( typeDto.getCodeType()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé");
		}
        Type entityToSave = transformer.convertToEntity(typeDto);
        log.info("-- create type end.");
	    return transformer.convertToDto(typeRepository.save(entityToSave));
	}
	
	public TypeDto updateType(TypeDto typeDto){
		log.info("-- update type begin ...");
		if( typeDto.getIdType() == null ) {
			throw new IllegalStateException("idType est un champ obligatoire pour ce traitement");
		}
		
		Type typeToUpdate = typeRepository.findByIdType(typeDto.getIdType().longValue());
		
		if(typeToUpdate == null) {
			throw new IllegalStateException("Type inexistant");
		}
		
		if ( !typeDto.getCodeType().equals(typeToUpdate.getCodeType()) 
				&& typeRepository.findByCodeType( typeDto.getCodeType()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé ! ");
		}

		if(typeDto.getCodeType() != null)  typeToUpdate.setCodeType(typeDto.getCodeType());
		if(typeDto.getLibelleType() != null)  typeToUpdate.setLibelleType(typeDto.getLibelleType());
		if(typeDto.getDescriptionType() != null)  typeToUpdate.setDescriptionType(typeDto.getDescriptionType());
		
        Type entityToSave = transformer.convertToEntity(typeDto);
        log.info("-- update type end.");
	    return transformer.convertToDto(typeRepository.save(entityToSave));
	}
	
	

}
