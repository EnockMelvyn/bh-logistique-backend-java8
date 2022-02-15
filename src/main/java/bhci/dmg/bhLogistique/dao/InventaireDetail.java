package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity(name="t_inventaire_detail") 
@Data
@NoArgsConstructor
public class InventaireDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventaire_id")
    @JsonIgnore
	private Inventaire inventaire;
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	private Integer qteCalcule;
	private Integer qteComptee;
	private Double cmup;
	
	
}
