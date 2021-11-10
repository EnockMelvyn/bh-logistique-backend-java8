package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_sousFamille")
@Data
public class SousFamille implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "code_sous_famille", unique = true)
    private String codeSousFamille;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sous_famille", nullable = false)
    private Long idSousFamille;

    @Column(name = "libelle_sous_famille")
    private String libelleSousFamille;

    @ManyToOne
    @JoinColumn(name = "famille_id")
    private Famille famille;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public SousFamille() {
    }

    public SousFamille(String libelleSousFamille, String codeSousFamille,Famille famille){
        this.libelleSousFamille = libelleSousFamille;
        this.codeSousFamille = codeSousFamille;
        this.famille = famille;
    }

}
