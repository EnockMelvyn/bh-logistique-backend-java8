package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bhci.dmg.bhLogistique.enums.StatutDemande;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_demande")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Demande implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_demande", nullable = false)
    private Long idDemande;

    @Column(name = "num_ref")
    private String numRef;

    @Column(name = "estimation")
    private int estimation;

    @Column(name = "observations")
    private String observation;

    @Column(name = "date_demande")
    private LocalDateTime dateDemande;

    @Column(name = "demandeur")
    private String demandeur;
    
    @ManyToOne
    @JoinColumn(name = "directionDemandeur")
    private Direction directionDemandeur;

    @Column(name = "statut")
    private String statutDemande = StatutDemande.EN_ATTENTE.getValue();
    
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorieDemande;
    
    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type typeDemande;

    @Column(name = "urgent")
    private boolean urgent;

    @Column(name = "justif_urgence")
    private String justifUrgence;

    @Column(name = "motif_rejet")
    private String motifRejet;

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
    mappedBy = "demande"/*,
    cascade = CascadeType.ALL,
    orphanRemoval = true*/
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<DemandeArticle> demandeArticles;

    public void addArticles(DemandeArticle demandeArticle) {
        demandeArticles.add(demandeArticle);
        demandeArticle.setDemande(this);
    }

    public void removeArticle(DemandeArticle demandeArticle) {
        demandeArticles.remove(demandeArticle);
        demandeArticle.setDemande(null);
    }
}
