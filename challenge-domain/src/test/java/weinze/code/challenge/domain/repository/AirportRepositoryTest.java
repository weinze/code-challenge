package weinze.code.challenge.domain.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.model.insight.AirportFilter;

public class AirportRepositoryTest {

    @Spy
    @InjectMocks
    private AirportRepository airportRepository;

    @Mock
    private CountryRepository countryRepository;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);

        final Airport a1 = airport("AR");
        final Airport a2 = airport("AR");
        final Airport a3 = airport("BR");

        final Country c1 = country("Argentina", "AR");
        final Country c2 = country("Brazil", "BR");

        Mockito.when(airportRepository.findAll()).thenReturn(ImmutableList.of(a1, a2, a3));
        Mockito.when(countryRepository.findAll()).thenReturn(ImmutableList.of(c1, c2));
    }

    @DataProvider
    private Object[][] findAllByCodeProvider() {
        return new Object[][] {
                {null, 0},
                {"", 0},
                {"AR", 2},
                {"ar", 2},
                {"BR", 1},
                {"XX", 0},
        };
    }

    @Test(dataProvider = "findAllByCodeProvider")
    public void findAllByCode(String code, int expected) {
        final AirportFilter filter = new AirportFilter();
        filter.setCountryCode(code);

        List<Airport> result = airportRepository.findAll(filter);

        assertNotNull(result);
        assertEquals(result.size(), expected);
    }

    @DataProvider
    private Object[][] findAllByNameProvider() {
        return new Object[][] {
                {null, 0},
                {"", 0},
                {"Argentina", 2},
                {"argentina", 2},
                {"Brazil", 1},
                {"XX", 0},
        };
    }

    @Test(dataProvider = "findAllByNameProvider")
    public void findAllByName(String name, int expected) {
        final AirportFilter filter = new AirportFilter();
        filter.setCountryName(name);

        List<Airport> result = airportRepository.findAll(filter);

        assertNotNull(result);
        assertEquals(result.size(), expected);
    }

    private Airport airport(String isoCountry) {
        final Airport airport = new Airport();
        airport.setIsoCountry(isoCountry);
        return airport;
    }

    private Country country(String name, String code) {
        final Country country = new Country();
        country.setName(name);
        country.setCode(code);
        return country;
    }
}
