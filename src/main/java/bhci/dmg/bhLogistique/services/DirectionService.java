package bhci.dmg.bhLogistique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.Direction;
import bhci.dmg.bhLogistique.repository.DirectionRepository;

@Service
public class DirectionService {

	@Autowired
	DirectionRepository directionRepository;
	
	public List<Direction>getAllDirections() {
		return directionRepository.findAll();
	}
}
