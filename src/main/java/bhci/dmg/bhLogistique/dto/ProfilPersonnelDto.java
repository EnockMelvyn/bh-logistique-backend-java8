package bhci.dmg.bhLogistique.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProfilPersonnelDto {

	private Long id;
	private String matricule;
	private String email;
	private Long idPerso;
	private List<String> codesProfil;
}
