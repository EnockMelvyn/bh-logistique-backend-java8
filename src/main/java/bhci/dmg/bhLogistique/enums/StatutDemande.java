package bhci.dmg.bhLogistique.enums;

public enum StatutDemande {
    EN_ATTENTE ("EN_ATTENTE"),
    VALIDEE_PAR_SUPERIEUR ("VALIDEE_PAR_SUPERIEUR"),
    VALIDEE_POUR_TRAITEMENT("VALIDEE_POUR_TRAITEMENT"),
    REJETEE("REJETEE"),
    VISA_DEMANDEUR("VISA_DEMANDEUR"),
    TRAITEE("TRAITEE");

    private String value;

    StatutDemande( String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


}
