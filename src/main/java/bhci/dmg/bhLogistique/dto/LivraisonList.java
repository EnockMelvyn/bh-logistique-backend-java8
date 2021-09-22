package bhci.dmg.bhLogistique.dto;

import bhci.dmg.bhLogistique.dao.Fournisseur;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
public class LivraisonList {
    private static final long serialVersionUID = 1L;

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
