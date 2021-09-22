package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_famille")
@Data
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "code_famille", unique = true)
    private String codeFamille;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_famille", nullable = false)
    private Long idFamille;

    @Column(name = "libelle_famille")
    private String libelleFamille;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public Famille() {
    }

    public Famille(String libelleFamille, String codeFamille){
        this.libelleFamille = libelleFamille;
        this.codeFamille = codeFamille;
    }

}
