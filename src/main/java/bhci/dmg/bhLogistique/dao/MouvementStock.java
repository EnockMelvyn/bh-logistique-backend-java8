package bhci.dmg.bhLogistique.dao;

import bhci.dmg.bhLogistique.enums.TypeMouvement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_mouvement_stock")
@Data
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mouvement_stock", nullable = false)
    private Long idMouvementStock;

    private LocalDateTime dateMouvement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    
    private Article article;
    private double qteAvant;
    private double qteMouvement;
    private TypeMouvement typeMouvement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    @JsonIgnore
    private Fournisseur fournisseur;

    private String username;
    private String demandeur;
    private Double prixUnitaire;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public MouvementStock() {
    }

    public MouvementStock(LocalDateTime dateMouvement, Article article, double qteAvant,double qteMouvement, TypeMouvement typeMouvement, Double prixUnitaire) {
        this.dateMouvement = dateMouvement;
        this.article = article;
        this.qteAvant = qteAvant;
        this.qteMouvement = qteMouvement;
        this.typeMouvement = typeMouvement;
        this.prixUnitaire = prixUnitaire;
    }
}
