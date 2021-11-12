package bhci.dmg.bhLogistique.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
public class LivraisonList {

    @NotNull
    private LocalDate dateLivraison;

    @NotNull
    private String fournisseurNomFournisseur;

    @Id
    @NotNull
    private Long idLivraison;

    @NotNull
    private String numeroBl;


}
