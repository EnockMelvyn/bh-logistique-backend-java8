package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_demande_direction_details")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DemandeDirectionDetails implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantite_demande")
    private int quantiteDemande;
    
    @Column(name = "quantite_valide_direction")
    private int quantiteValideDir;
    
    @Column(name = "quantite_valide_dmg")
    private int quantiteValideDmg;

    @Column(name = "quantite_sortie_dmg")
    private int quantiteSortieDmg;
    
    @Column(name = "quantite_recue_direction")
    private int quantiteRecueDir;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_direction_id")
    @JsonIgnore
    private DemandeDirection demandeDirection;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public DemandeDirectionDetails() {
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandeDirectionDetails )) return false;
        return id != null && id.equals(((DemandeDirectionDetails) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
