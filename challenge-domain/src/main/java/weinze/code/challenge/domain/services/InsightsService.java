package weinze.code.challenge.domain.services;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.model.Runway;
import weinze.code.challenge.domain.repository.AirportRepository;
import weinze.code.challenge.domain.repository.CountryRepository;
import weinze.code.challenge.domain.repository.RunwayRepository;

public class InsightsService {

    private AirportRepository airportRepository;
    private CountryRepository countryRepository;
    private RunwayRepository runwayRepository;

    @Inject
    public InsightsService(AirportRepository airportRepository, CountryRepository countryRepository, RunwayRepository runwayRepository) {
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
        this.runwayRepository = runwayRepository;
    }

    public Map<Country, Long> topCountriesWithMoreAirports(int limit, boolean desc) {

        final Comparator<Long> comparator = desc ? naturalOrder() : reverseOrder();

        final Map<String, Long> countByCountry = stream(airportRepository.findAll()).collect(groupingBy(Airport::getIsoCountry, Collectors.counting()));

        return stream(countByCountry.entrySet())
                .sorted(comparingByValue(comparator))
                .limit(limit).map(e -> countryRepository.findByCode(e.getKey()))
                .collect(toMap(identity(), c -> countByCountry.get(c.getCode())));
    }

    public Map<Country, List<String>> runwaysByCountry() {

        final Map<Long, List<Runway>> airportWithRunways = stream(runwayRepository.findAll()).collect(groupingBy(Runway::getAirportRef));

        final Map<String, List<Airport>> countryWithAirport = stream(airportRepository.findAll()).collect(groupingBy(Airport::getIsoCountry));

        final Function<Country, List<String>> countryWithRunways = country -> countryWithAirport.get(country.getCode())
                .stream()
                .filter(airport -> airportWithRunways.get(airport.getId()) != null)
                .flatMap(airport -> airportWithRunways.get(airport.getId()).stream())
                .map(Runway::getSurface).distinct()
                .collect(toList());

        return stream(countryWithAirport.entrySet())
                .map(e -> countryRepository.findByCode(e.getKey()))
                .collect(toMap(identity(), countryWithRunways));

    }

    public List<String> topRunways(int limit) {
        final Map<String, Long> countByLeIdent = stream(runwayRepository.findAll()).collect(groupingBy(Runway::getLeIdent, Collectors.counting()));
        return stream(countByLeIdent.entrySet()).sorted(comparingByValue(reverseOrder())).limit(limit).map(Entry::getKey).collect(Collectors.toList());
    }
}