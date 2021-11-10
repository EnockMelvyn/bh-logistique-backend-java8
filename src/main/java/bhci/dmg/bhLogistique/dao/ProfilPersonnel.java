package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="t_profil_personnel")
public class ProfilPersonnel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="profil_id")
	private Profil profil;
	@ManyToOne
	@JoinColumn(name="personnel_id")
	private Personnel personnel;
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof ProfilPersonnel )) return false;
	        return id != null && id.equals(((ProfilPersonnel) o).getId());
	    }

	    @Override
	    public int hashCode() {
	        return getClass().hashCode();
	    }
}
