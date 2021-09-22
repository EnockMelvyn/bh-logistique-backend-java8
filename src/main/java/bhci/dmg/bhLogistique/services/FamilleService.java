package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Famille;
import bhci.dmg.bhLogistique.repository.FamilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilleService {

    @Autowired
    FamilleRepository familleRepository;

    public List<Famille> getAllFamilles() {
        /*List<Famille> familles = new ArrayList<Famille>();
        familleRepository.findAll().forEach(familles::add);*/
        return familleRepository.findAll();
    }

    public Famille getFamilleByCode(String codeFamille) {
        return familleRepository.findByCodeFamille(codeFamille).orElseThrow(() ->
                new IllegalStateException(" Le code famille:" + codeFamille +" n'existe pas")
        );
    }

    public Famille getFamilleById(Long idFamille){
        return  familleRepository.findById(idFamille).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idFamille +" n'existe pas")
        );
    }

    public Famille updateFamille(Long idFamille, Famille famille){
        Famille familleToUpdate = familleRepository.findById(idFamille).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idFamille +" n'existe pas")
        );

        boolean familleCodeExists = familleRepository.findByCodeFamille(famille.getCodeFamille()).isPresent();
        if (familleCodeExists) {
            Long id = familleRepository.findByCodeFamille(famille.getCodeFamille()).get().getIdFamille();
            System.out.println("id frontend:"+idFamille+" , idbackend:"+id);
            if (idFamille != id) {
                throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
            }
        }
        familleToUpdate.setCodeFamille(famille.getCodeFamille());
        familleToUpdate.setLibelleFamille(famille.getLibelleFamille());

        return familleRepository.save(familleToUpdate);
    }

    public Famille createFamille(Famille famille) {
        boolean familleExists = familleRepository.findByCodeFamille(famille.getCodeFamille()).isPresent();

        if (familleExists) {
            throw new IllegalStateException("Ce code famille est déjà utilisé");
        }

        return familleRepository.save(new Famille(famille.getLibelleFamille(), famille.getCodeFamille()));
    }
}
