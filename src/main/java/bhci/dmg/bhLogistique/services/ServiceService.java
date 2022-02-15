package bhci.dmg.bhLogistique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bhci.dmg.bhLogistique.dao.Service;
import bhci.dmg.bhLogistique.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {

	@Autowired
	ServiceRepository serviceRepo;
	
	public List<Service> getAllServices(){
		return serviceRepo.findAll();
	}
}
