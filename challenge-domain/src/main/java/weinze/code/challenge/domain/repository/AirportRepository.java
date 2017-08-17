package weinze.code.challenge.domain.repository;

import static java.util.stream.Collectors.toList;
import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.model.insight.AirlineFilter;

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


    // TODO filtrar con una lista de Predicate
    public List<Airport> findAll(AirlineFilter filter) {
        Stream<Airport> stream = stream(findAll());

        if(StringUtils.isNotBlank(filter.getCountryCode())) {
            stream = stream.filter(a -> a.getIsoCountry().equalsIgnoreCase(filter.getCountryCode()));
        }

        if(StringUtils.isNotBlank(filter.getCountryName())) {
            final Optional<Country> country = this.countryRepository.findAll().stream().filter(c -> c.getName().equalsIgnoreCase(filter.getCountryName())).findFirst();
            // TODO Si no encuentra la ciudad, no filtra por ningun resultado y devuelve todos! No debería devolver nada...
            if(country.isPresent()) {
                stream = stream.filter(a -> a.getIsoCountry().equals(country.get().getCode()));
            }
        }

        return stream.collect(toList());
    }

    private Predicate<Airport> filterByIsoCountry(String country) {
        return a -> a.getIsoCountry().equals(country);
    }

    public List<Airport> findAllByCountryCode(String countryCode) {
        return findAll().stream().filter(a -> a.getIsoCountry().equals(countryCode)).collect(toList());
    }

    public List<Airport> findAllByCountryName(String name) {
        final Optional<Country> country = this.countryRepository.findAll().stream().filter(c -> c.getName().equals(name)).findFirst();
        if(country.isPresent()) {
            return findAllByCountryCode(country.get().getCode());
        }
        return ImmutableList.of();
    }
}
