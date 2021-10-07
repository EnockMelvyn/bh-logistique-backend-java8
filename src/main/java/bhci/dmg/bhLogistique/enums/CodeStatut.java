package bhci.dmg.bhLogistique.enums;

public enum CodeStatut {
    EN_ATTENTE ("ATT"),
    EN_COURS("ENC");

    private String value;

    CodeStatut( String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
