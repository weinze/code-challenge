package weinze.code.challenge.domain.model;

import java.math.BigDecimal;

public class Runway extends PersistentEntity {

    private Long airportRef;
    private String airportIdent;
    private Long lengthFt;
    private Long widthFt;
    private String surface;
    private Boolean lighted;
    private Boolean closed;
    private String leIdent;
    private BigDecimal leLatitudeDeg;
    private BigDecimal leLongitudeDeg;
    private BigDecimal leElevationFt;
    private BigDecimal leHeadingDegT;
    private BigDecimal leDisplacedThresholdFt;
    private String heIdent;
    private BigDecimal heLatitudeDeg;
    private BigDecimal heLongitudeDeg;
    private BigDecimal heElevationFt;
    private BigDecimal heHeadingDegT;
    private BigDecimal heDisplacedThresholdFt;

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

    public Long getLengthFt() {
        return lengthFt;
    }

    public void setLengthFt(Long lengthFt) {
        this.lengthFt = lengthFt;
    }

    public Long getWidthFt() {
        return widthFt;
    }

    public void setWidthFt(Long widthFt) {
        this.widthFt = widthFt;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Boolean getLighted() {
        return lighted;
    }

    public void setLighted(Boolean lighted) {
        this.lighted = lighted;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getLeIdent() {
        return leIdent;
    }

    public void setLeIdent(String leIdent) {
        this.leIdent = leIdent;
    }

    public BigDecimal getLeLatitudeDeg() {
        return leLatitudeDeg;
    }

    public void setLeLatitudeDeg(BigDecimal leLatitudeDeg) {
        this.leLatitudeDeg = leLatitudeDeg;
    }

    public BigDecimal getLeLongitudeDeg() {
        return leLongitudeDeg;
    }

    public void setLeLongitudeDeg(BigDecimal leLongitudeDeg) {
        this.leLongitudeDeg = leLongitudeDeg;
    }

    public BigDecimal getLeElevationFt() {
        return leElevationFt;
    }

    public void setLeElevationFt(BigDecimal leElevationFt) {
        this.leElevationFt = leElevationFt;
    }

    public BigDecimal getLeHeadingDegT() {
        return leHeadingDegT;
    }

    public void setLeHeadingDegT(BigDecimal leHeadingDegT) {
        this.leHeadingDegT = leHeadingDegT;
    }

    public BigDecimal getLeDisplacedThresholdFt() {
        return leDisplacedThresholdFt;
    }

    public void setLeDisplacedThresholdFt(BigDecimal leDisplacedThresholdFt) {
        this.leDisplacedThresholdFt = leDisplacedThresholdFt;
    }

    public String getHeIdent() {
        return heIdent;
    }

    public void setHeIdent(String heIdent) {
        this.heIdent = heIdent;
    }

    public BigDecimal getHeLatitudeDeg() {
        return heLatitudeDeg;
    }

    public void setHeLatitudeDeg(BigDecimal heLatitudeDeg) {
        this.heLatitudeDeg = heLatitudeDeg;
    }

    public BigDecimal getHeLongitudeDeg() {
        return heLongitudeDeg;
    }

    public void setHeLongitudeDeg(BigDecimal heLongitudeDeg) {
        this.heLongitudeDeg = heLongitudeDeg;
    }

    public BigDecimal getHeElevationFt() {
        return heElevationFt;
    }

    public void setHeElevationFt(BigDecimal heElevationFt) {
        this.heElevationFt = heElevationFt;
    }

    public BigDecimal getHeHeadingDegT() {
        return heHeadingDegT;
    }

    public void setHeHeadingDegT(BigDecimal heHeadingDegT) {
        this.heHeadingDegT = heHeadingDegT;
    }

    public BigDecimal getHeDisplacedThresholdFt() {
        return heDisplacedThresholdFt;
    }

    public void setHeDisplacedThresholdFt(BigDecimal heDisplacedThresholdFt) {
        this.heDisplacedThresholdFt = heDisplacedThresholdFt;
    }
}
