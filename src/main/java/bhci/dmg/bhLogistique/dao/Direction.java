package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_direction")
@Entity
@Data
public class Direction implements Serializable {

	@Id
	private Long idDirection;
	private String codeDirection;
	private String libelleDirection;
	private boolean isActive;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime modifiedAt;
	private String modifiedBy;
	private String valideur;
}
