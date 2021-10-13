package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_commande")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commande", nullable = false)
    private Long idCommande;

    @Column(name = "numero_commande",unique = true)
    private String numeroCommande;
    
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @Column(name = "date_commande")
    private LocalDate dateCommande;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "commande",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<CommandeDetail> commandeDetails;

    public void addCommandeDetail(CommandeDetail cmdDet) {
        this.commandeDetails.add(cmdDet);
        cmdDet.setCommande(this);
    }

    public void removeCommandeDetail(CommandeDetail cmdDet) {
        this.commandeDetails.remove(cmdDet);
        cmdDet.setCommande(null);
    }
}
