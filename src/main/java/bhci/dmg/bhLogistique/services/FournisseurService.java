package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Fournisseur;
import bhci.dmg.bhLogistique.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {
    @Autowired
    FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseurs(){
        return this.fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurByCode(String codeFournisseur) {
        return fournisseurRepository.findByCodeFournisseur(codeFournisseur).orElseThrow(() ->
                new IllegalStateException(" Le code sous famille:" + codeFournisseur +" n'existe pas")
        );
    }
    public Fournisseur getFournisseurById(Long idFournisseur){
        return  fournisseurRepository.findById(idFournisseur).orElseThrow(() ->
                new IllegalStateException(" L'id fournisseur:" + idFournisseur +" n'existe pas")
        );
    }

    public Fournisseur deleteFournisseurById(Long idFournisseur){
        return  fournisseurRepository.findById(idFournisseur).orElseThrow(() ->
                new IllegalStateException(" L'id fournisseur:" + idFournisseur +" n'existe pas")
        );
    }

    public Fournisseur updateFournisseur(Long idFournisseur, Fournisseur fournisseur){
        Fournisseur fournisseurToUpdate = fournisseurRepository.findById(idFournisseur).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idFournisseur +" n'existe pas")
        );

        boolean fournisseurCodeExists = fournisseurRepository.findByCodeFournisseur(fournisseur.getCodeFournisseur()).isPresent();

        if (fournisseurCodeExists) {
            Long id = fournisseurRepository.findByCodeFournisseur(fournisseur.getCodeFournisseur()).get().getIdFournisseur();
            if (idFournisseur != id) {
                throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
            }
        }

        fournisseurToUpdate.setCodeFournisseur(fournisseur.getCodeFournisseur());

        fournisseurToUpdate.setNomFournisseur(fournisseur.getNomFournisseur());
        fournisseurToUpdate.setContactFournisseur(fournisseur.getContactFournisseur());
        return fournisseurRepository.save(fournisseurToUpdate);
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        boolean fournisseurExists = fournisseurRepository.findByCodeFournisseur(fournisseur.getCodeFournisseur()).isPresent();

        if (fournisseurExists) {
            throw new IllegalStateException("Ce code sous-famille est déjà utilisé");
        }

        return fournisseurRepository.save(new Fournisseur(fournisseur.getNomFournisseur(), fournisseur.getCodeFournisseur(), fournisseur.getContactFournisseur() ));
    }
}
