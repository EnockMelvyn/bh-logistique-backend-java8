package bhci.dmg.bhLogistique.dao;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table( name = "t_sortie")
@Entity
@Data
public class Sortie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSortie;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

    private String reference;

    private Integer quantite;

    private LocalDate dateSortie;

    private final LocalDate createdAt = LocalDate.now();
    private String createdBy;

    private LocalDate modifiedAt;
    private String modifiedBy;
}
