package weinze.code.challenge.domain.model;

public class Runway extends PersistentEntity {

    private Long airportRef;
    private String airportIdent;
    private String surface;
    private String leIdent;

    public Long getAirportRef() {
        return airportRef;
    }

    public void setAirportRef(Long airportRef) {
        this.airportRef = airportRef;
    }

    public String getAirportIdent() {
        return airportIdent;
    }

    public void setAirportIdent(String airportIdent) {
        this.airportIdent = airportIdent;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getLeIdent() {
        return leIdent;
    }

    public void setLeIdent(String leIdent) {
        this.leIdent = leIdent;
    }
}
