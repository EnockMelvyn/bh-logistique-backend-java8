package bhci.dmg.bhLogistique.dao;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
