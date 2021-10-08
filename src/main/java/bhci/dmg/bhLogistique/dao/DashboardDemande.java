package bhci.dmg.bhLogistique.dao;

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
@Table(name="dashboard_demande_view")
public class DashboardDemande {

	private Double nombre;
	@Id
	private String demandeur;
	private String statut;
}
