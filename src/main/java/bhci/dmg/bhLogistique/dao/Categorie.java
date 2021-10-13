package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Table( name = "t_categorie")
@Entity
@Data
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_categorie")
    private Long idCategorie;
    @Column(name = "libelle_categorie")
    private String libelleCategorie;
    @Column(name = "code_categorie")
    private String codeCategorie;
    @Column(name = "description_categorie")
    private String descriptionCategorie;
    @OneToMany(
    	    mappedBy = "categorie", cascade = CascadeType.ALL)
    List<SousCategorie> sousCategorie;
}
