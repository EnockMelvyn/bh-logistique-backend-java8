package bhci.dmg.bhLogistique.dao;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table( name = "t_sortie")
@Entity
@Data
public class Sortie implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7731740352682520306L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSortie;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "demande_direction_id")
    private DemandeDirection demandeDirection;
    
    private String reference;

    private Integer quantite;

    private LocalDateTime dateSortie;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;

    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
