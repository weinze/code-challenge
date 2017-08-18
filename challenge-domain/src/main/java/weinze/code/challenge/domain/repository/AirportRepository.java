package weinze.code.challenge.domain.repository;

import static java.util.stream.Collectors.toList;
import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.model.insight.AirportFilter;

@Singleton
public class AirportRepository extends AbstractRepository<Airport> {

    private List<Airport> airports;

    private CountryRepository countryRepository;

    @Inject
    public AirportRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    protected List<Airport> cachedEntities() {
        return this.airports;
    }

    @Override
    protected void cachedEntities(List<Airport> entities) {
        this.airports = entities;
    }

    public List<Airport> findAll(AirportFilter filter) {

        if(StringUtils.isNotBlank(filter.getCountryCode())) {
            return stream(findAll()).filter(filterByIsoCountry(filter.getCountryCode())).collect(toList());
        } else if(StringUtils.isNotBlank(filter.getCountryName())) {
            final Optional<Country> country = stream(this.countryRepository.findAll())
                    .filter(c -> c.getName().equalsIgnoreCase(filter.getCountryName()))
                    .findFirst();
            if(country.isPresent()) {
                return stream(findAll()).filter(filterByIsoCountry(country.get().getCode())).collect(toList());
            }
        }

        return Lists.newArrayList();
    }

    private Predicate<Airport> filterByIsoCountry(String country) {
        return a -> a.getIsoCountry().equalsIgnoreCase(country);
    }
}
