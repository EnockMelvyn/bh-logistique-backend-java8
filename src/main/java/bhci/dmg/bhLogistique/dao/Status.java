package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;

@Table( name = "t_status")
@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_statut")
    private Long idStatut;
    @Column(name = "libelle_statut")
    private String libelleStatut;
    @Column(name = "code_statut")
    private String codeStatut;
    @Column(name = "description_statut")
    private String descriptionStatut;
}
