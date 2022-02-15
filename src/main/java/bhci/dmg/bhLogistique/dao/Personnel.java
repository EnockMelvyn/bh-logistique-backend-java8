package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name="t_personnel")
public class Personnel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	private String matricule;
	@ManyToOne
	@JoinColumn(name="direction_id")
	private Direction direction;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	@OneToMany(
	mappedBy = "personnel",
    cascade = CascadeType.ALL,
    orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<ProfilPersonnel> profils;
	
	public void addProfil(ProfilPersonnel profilPerso) {
        profils.add(profilPerso);
        profilPerso.setPersonnel(this);
    }

    public void removeProfil(ProfilPersonnel profilPerso) {
    	profils.remove(profilPerso);
        profilPerso.setPersonnel(null);
    }
}
