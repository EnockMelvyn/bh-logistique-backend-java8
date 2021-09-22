package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_commande_detail")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class CommandeDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_commande_detail", nullable = false)
    private Long idCommandeDetail;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    @JsonIgnore
    private Commande commande;

    @Column(name = "quantite")
    private Integer quantite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandeDetail)) return false;
        return idCommandeDetail != null && idCommandeDetail.equals(((CommandeDetail) o).getIdCommandeDetail());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
