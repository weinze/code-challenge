package weinze.code.challenge.domain.model;

public class Airport extends PersistentEntity {

    private String ident;
    private String isoCountry;

    public Airport() {}

    public Airport(String country) {
        this.isoCountry = country;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }
}
