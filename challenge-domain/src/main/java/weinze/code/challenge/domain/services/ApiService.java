package weinze.code.challenge.domain.services;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.insight.CountryWithAirports;
import weinze.code.challenge.domain.model.insight.CountryWithRunways;

public class ApiService {

    private InsightsService insightsService;

    @Inject
    public ApiService(InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    public List<CountryWithAirports> topCountriesWithMoreAirports(int limit, boolean desc) {
        return this.insightsService.topCountriesWithMoreAirports(limit, desc).entrySet().stream().map(e -> new CountryWithAirports(e.getKey(), e.getValue())).collect(toList());
    }

    public List<CountryWithRunways> runwaysByCountry() {
        return this.insightsService.runwaysByCountry().entrySet().stream().map(e -> new CountryWithRunways(e.getKey(), e.getValue())).collect(toList());
    }

    public List<String> topRunways(int limit) {
        return this.insightsService.topRunways(limit);
    }
}
