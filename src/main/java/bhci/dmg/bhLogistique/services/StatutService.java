/**
 * 
 */
package bhci.dmg.bhLogistique.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bhci.dmg.bhLogistique.dao.Status;
import bhci.dmg.bhLogistique.dto.StatusDto;
import bhci.dmg.bhLogistique.repository.StatusRepository;
import bhci.dmg.bhLogistique.transformer.Transformer;
import lombok.extern.java.Log;

/**
 * @author ikouame
 *
 */

@Log
@Service
public class StatutService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	private Transformer<StatusDto, Status> transformer = new Transformer<StatusDto, Status>(StatusDto.class,
			Status.class);
	
	
	public List<StatusDto> getAllStatus(){
		log.info("-- get all status ...");
		List<StatusDto> response = new ArrayList<StatusDto>();
        List<Status> allStatus = statusRepository.findAll();
        if(allStatus != null && !allStatus.isEmpty()) 
        	response = transformer.convertToDto(allStatus);
        log.info("-- get all status finish.");
		return response;
	}
	
	public StatusDto getStatusByCode(String code){
		log.info("-- get status by code...");
		StatusDto response = new StatusDto();
        Status status = statusRepository.findByCodeStatut(code);
        if(status != null ) 
        	response = transformer.convertToDto(status);
        log.info("-- get all status finish.");
		return response;
	}
	
	
	public StatusDto createStatus(StatusDto statusDto){
		log.info("-- create status begin ...");
		if( statusDto.getCodeStatut() == null  || statusDto.getLibelleStatut() == null
				|| statusDto.getDescriptionStatut() == null ) {
			throw new IllegalStateException("code, libelle et description sont les champs obligatoires");
		}
		
		if (statusRepository.findByCodeStatut( statusDto.getCodeStatut()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé");
		}
        Status entityToSave = transformer.convertToEntity(statusDto);
        log.info("-- create status end.");
	    return transformer.convertToDto(statusRepository.save(entityToSave));
	}
	
	public StatusDto updateStatus(StatusDto statusDto){
		log.info("-- update status begin ...");
		if( statusDto.getIdStatut() == null ) {
			throw new IllegalStateException("idStatut est un champ obligatoire pour ce traitement");
		}
		
		Status statusToUpdate = statusRepository.findByIdStatut(statusDto.getIdStatut().longValue());
		
		if(statusToUpdate == null) {
			throw new IllegalStateException("Statut inexistant");
		}
		
		if ( !statusDto.getCodeStatut().equals(statusToUpdate.getCodeStatut()) 
				&& statusRepository.findByCodeStatut( statusDto.getCodeStatut()) != null) {
			throw new IllegalStateException("Le code fourni est déjà utilisé ! ");
		}

		if(statusDto.getCodeStatut() != null)  statusToUpdate.setCodeStatut(statusDto.getCodeStatut());
		if(statusDto.getLibelleStatut() != null)  statusToUpdate.setLibelleStatut(statusDto.getLibelleStatut());
		if(statusDto.getDescriptionStatut() != null)  statusToUpdate.setDescriptionStatut(statusDto.getDescriptionStatut());
		
        Status entityToSave = transformer.convertToEntity(statusDto);
        log.info("-- update status end.");
	    return transformer.convertToDto(statusRepository.save(entityToSave));
	}
	
	

}
