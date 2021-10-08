package bhci.dmg.bhLogistique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bhci.dmg.bhLogistique.dao.DashboardDemande;
import bhci.dmg.bhLogistique.repository.DashboardDemandeRepository;

@Service
public class DashboardDemandeService {
	@Autowired
	DashboardDemandeRepository dashboardDemandeRepository;
	
	public List<DashboardDemande> getDashboardDemande(String demandeur) {
		return dashboardDemandeRepository.findByDemandeur(demandeur);
	}
}
