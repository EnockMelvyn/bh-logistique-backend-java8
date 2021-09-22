package bhci.dmg.bhLogistique.dao;

import bhci.dmg.bhLogistique.repository.FournisseurRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_fournisseur")
@Data
public class Fournisseur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fournisseur", nullable = false)
    private Long idFournisseur;
    private String nomFournisseur;
    private String codeFournisseur;
    private String contactFournisseur;



    public Fournisseur(){
    }

    public Fournisseur( String nomFournisseur, String codeFournisseur, String contactFournisseur){
        this.codeFournisseur = codeFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.contactFournisseur = contactFournisseur;
    }

}
