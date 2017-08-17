package weinze.code.challenge.domain.model.insight;

import weinze.code.challenge.domain.model.Country;

public class CountryWithAirports {

    private Country country;
    private Long airports;

    public CountryWithAirports(Country country, Long airports) {
        this.country = country;
        this.airports = airports;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getAirports() {
        return airports;
    }

    public void setAirports(Long airports) {
        this.airports = airports;
    }
}
