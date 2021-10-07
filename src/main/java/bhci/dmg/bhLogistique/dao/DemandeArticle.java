package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_demande_article")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DemandeArticle implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id_demande_article", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDemandeArticle;

    @Column(name = "quantite")
    private int quantite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id")
    @JsonIgnore
    private Demande demande;

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

    public DemandeArticle() {
    }

    public DemandeArticle(int quantite, Demande demande, Article article) {
        this.quantite = quantite;
        this.demande = demande;
        this.article = article;
        this.isDeleted = Boolean.FALSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemandeArticle )) return false;
        return idDemandeArticle != null && idDemandeArticle.equals(((DemandeArticle) o).getIdDemandeArticle());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
