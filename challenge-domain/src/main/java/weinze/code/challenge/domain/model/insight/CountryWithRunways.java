package weinze.code.challenge.domain.model.insight;

import java.util.List;

import weinze.code.challenge.domain.model.Country;

public class CountryWithRunways {

    private Country country;
    private List<String> runways;

    public CountryWithRunways(Country country, List<String> runways) {
        this.country = country;
        this.runways = runways;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<String> getRunways() {
        return runways;
    }

    public void setRunways(List<String> runways) {
        this.runways = runways;
    }
}
