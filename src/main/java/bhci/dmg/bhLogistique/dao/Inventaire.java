package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity(name="t_inventaire")
@Data
@NoArgsConstructor
public class Inventaire  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_article", nullable = false)
	private Long id;
	private String libelle;
	private Famille famille;
	private SousFamille sousFamille;
	@ManyToOne
	private Status status;
	private LocalDateTime dateInventaire;
	private Double valeurEcart;
	
	@OneToMany(
    mappedBy = "inventaire",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<InventaireDetail> details;
	
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime modifiedAt;
	private String modifiedBy;

	
	public void addDetail(InventaireDetail det) {
        details.add(det);
        det.setInventaire(this);
    }

    public void removeDetail(InventaireDetail det) {
        details.remove(det);
        det.setInventaire(null);
    }
}
