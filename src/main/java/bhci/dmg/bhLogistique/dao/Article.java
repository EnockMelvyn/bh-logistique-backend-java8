package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_article")
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "code_article", unique = true)
    private String codeArticle;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_article", nullable = false)
    private Long idArticle;

    @Column(name = "libelle_article")
    private String libelleArticle;

    @Column(name = "quantite_stock")
    private int quantiteStock = 0;

    @Column(name = "stock_critique")
    private int stockCritique;

    @Column(name = "cmup")
    private double cmup;

    @ManyToOne
    @JoinColumn(name = "sous_famille_id")
    private SousFamille sousFamille;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public Article() {
    }

    public Article(String codeArticle, String libelleArticle, SousFamille sousFamille) {
        this.codeArticle = codeArticle;
        this.libelleArticle = libelleArticle;
        this.quantiteStock = 0;
        this.stockCritique = 1;
        this.cmup = 0;
        this.sousFamille = sousFamille;
    }
}
