package bhci.dmg.bhLogistique.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Immutable
@Table(name="demande_article_par_direction_view")
public class DemandeArticleParDirection {

	@Column(name = "quantite")
	private int quantite;
	@Id
	private Long id;
	@Column(name = "id_direction")
	private Long idDirection;
	@Column(name = "id_article")
	private Long idArticle;
	@Column(name = "statut")
	private String statut;
	@Column(name = "id_status")
	private Long idStatus;
}
