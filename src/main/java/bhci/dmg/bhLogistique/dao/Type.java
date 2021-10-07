package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;

@Table( name = "t_type")
@Entity
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type")
    private Long idType;
    @Column(name = "libelle_type")
    private String libelleType;
    @Column(name = "code_type")
    private String codeType;
    @Column(name = "description_type")
    private String descriptionType;
}
