
package bhci.dmg.bhLogistique.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class DemandeDto {
	
	private Integer idDemande;
    private String numRef;
    private Integer estimation;
    private String observation;
    private LocalDateTime dateDemande;
    private String statutDemande;
    private String demandeur;
    private Integer idStatus;
    private Integer idCategorie;
    private Long directionId;
    private Integer idType;
    private Boolean urgent;
    private String justifUrgence;
    private String motifRejet;
    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime modifiedAt;
    private String modifiedBy;
 
    private Boolean isDeleted;

    private List<DemandeArticleDto> demandeArticles;

}
