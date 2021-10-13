package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;

@Table( name = "t_sous_categorie")
@Entity
@Data
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sous_categorie")
    private Long idSousCategorie;
    @Column(name = "libelle_sous_categorie")
    private String libelleSousCategorie;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    @Column(name = "code_sous_categorie")
    private String codeSousCategorie;
    @Column(name = "description_sous_categorie")
    private String descriptionSousCategorie;
}
