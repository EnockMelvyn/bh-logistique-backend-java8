package bhci.dmg.bhLogistique.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity(name = "t_service")
@Data
public class Service implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private Long idService;
	private Long idDirection;
	private String codeService;
	private String libelleService;
	private boolean isActive;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime modifiedAt;
	private String modifiedBy;

}
