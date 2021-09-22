package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Famille;
import bhci.dmg.bhLogistique.dao.SousFamille;
import bhci.dmg.bhLogistique.repository.SousFamilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SousFamilleService {

    @Autowired
    SousFamilleRepository sousFamilleRepository;

    public List<SousFamille> getAllSousFamilles() {
        /*List<SousFamille> sousFamilles= new ArrayList<SousFamille>();
        sousFamilleRepository.findAll().forEach(sousFamilles::add);*/
        return sousFamilleRepository.findAll();
    }

    public SousFamille getSousFamilleByCode(String codeSousFamille) {
        return sousFamilleRepository.findByCodeSousFamille(codeSousFamille).orElseThrow(() ->
                new IllegalStateException(" Le code sous famille:" + codeSousFamille +" n'existe pas")
        );
    }

    public SousFamille getSousFamilleById(Long idSousFamille){
        return  sousFamilleRepository.findById(idSousFamille).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSousFamille +" n'existe pas")
        );
    }

    public SousFamille updateSousFamille(Long idSousFamille, SousFamille sousFamille){
        SousFamille sousFamilleToUpdate = sousFamilleRepository.findById(idSousFamille).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSousFamille +" n'existe pas")
        );

        boolean sousFamilleCodeExists = sousFamilleRepository.findByCodeSousFamille(sousFamille.getCodeSousFamille()).isPresent();

        if (sousFamilleCodeExists) {
            Long id = sousFamilleRepository.findByCodeSousFamille(sousFamille.getCodeSousFamille()).get().getIdSousFamille();
            if (idSousFamille != id) {
                throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
            }
        }

        sousFamilleToUpdate.setCodeSousFamille(sousFamille.getCodeSousFamille());
        sousFamilleToUpdate.setLibelleSousFamille(sousFamille.getLibelleSousFamille());
        sousFamilleToUpdate.setFamille(sousFamille.getFamille());

        return sousFamilleRepository.save(sousFamilleToUpdate);
    }

    public SousFamille createSousFamille(SousFamille sousFamille) {
        boolean sousFamilleExists = sousFamilleRepository.findByCodeSousFamille(sousFamille.getCodeSousFamille()).isPresent();

        if (sousFamilleExists) {
            throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
        }

        return sousFamilleRepository.save(new SousFamille(sousFamille.getLibelleSousFamille(), sousFamille.getCodeSousFamille(),sousFamille.getFamille()));
    }
}
