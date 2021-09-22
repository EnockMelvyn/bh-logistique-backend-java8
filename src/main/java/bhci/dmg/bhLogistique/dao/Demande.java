package bhci.dmg.bhLogistique.dao;

import bhci.dmg.bhLogistique.enums.StatutDemande;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate dateDemande;

    @Column(name = "demandeur")
    private String demandeur;

    @Column(name = "statut")
    private String statutDemande = StatutDemande.EN_ATTENTE.getValue();

    @Column(name = "urgent")
    private boolean urgent;

    @Column(name = "justif_urgence")
    private String justifUrgence;

    @Column(name = "motif_rejet")
    private String motifRejet;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @OneToMany(
    mappedBy = "demande",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
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
