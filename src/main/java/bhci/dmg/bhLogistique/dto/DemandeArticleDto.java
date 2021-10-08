/**
 * 
 */
package bhci.dmg.bhLogistique.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @author ikouame
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class DemandeArticleDto {
	
	private Integer idArticle;
	private Integer quantite;

}
