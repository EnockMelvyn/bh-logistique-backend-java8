package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_demande_direction")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DemandeDirection implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_ref")
    private String numRef;

    @Column(name = "observation_directeur")
    private String observationDirecteur;
    
    @Column(name = "observation_dmg")
    private String observationDmg;

    @Column(name = "date_demande")
    private LocalDateTime dateDemande;

    @Column(name = "demandeur")
    private String demandeur;
    
    @ManyToOne
    @JoinColumn(name = "direction")
    private Direction direction;
    
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
        
    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type typeDemande;



    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(
	    mappedBy = "demandeDirection",
	    cascade = CascadeType.ALL,
	    orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<DemandeDirectionDetails> demandeDirectionDetails;
    
    public void addDetail(DemandeDirectionDetails demDirDet) {
    	if (this.demandeDirectionDetails.contains(demDirDet)) {
    		this.demandeDirectionDetails.forEach(element -> {
    			if (element.getArticle().getIdArticle() == demDirDet.getArticle().getIdArticle()) {
    				element.setQuantiteDemande(element.getQuantiteDemande()+demDirDet.getQuantiteDemande());
    			}
    		});
    	}
		else {
	        this.demandeDirectionDetails.add(demDirDet);
	        demDirDet.setDemandeDirection(this);
        }
    }

    public void removeDetail(DemandeDirectionDetails demDirDet) {
    	this.demandeDirectionDetails.remove(demDirDet);
    	demDirDet.setDemandeDirection(null);
    }
    
}
