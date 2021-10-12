package bhci.dmg.bhLogistique.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class SousCategorieDto {
    private Integer idSousCategorie;
    private Integer categorieIdCategorie;
    private String libelleSousCategorie;
    private String codeSousCategorie;
    private String descriptionSousCategorie;
}
