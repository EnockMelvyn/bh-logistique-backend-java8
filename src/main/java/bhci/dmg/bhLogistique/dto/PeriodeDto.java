package bhci.dmg.bhLogistique.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class PeriodeDto {

	private LocalDateTime debut;
	private LocalDateTime fin;
}
