package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_livraison_detail")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class LivraisonDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_livraison_detail", nullable = false)
    private Long idLivraisonDetail;

    @ManyToOne
    @JoinColumn(name = "livraison_id")
    @JsonIgnore
    private Livraison livraison;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "quantite")
    private Integer quantite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivraisonDetail )) return false;
        return idLivraisonDetail != null && idLivraisonDetail.equals(((LivraisonDetail) o).getIdLivraisonDetail());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
