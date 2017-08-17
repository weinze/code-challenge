package weinze.code.challenge.domain.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.model.Runway;
import weinze.code.challenge.domain.repository.AirportRepository;
import weinze.code.challenge.domain.repository.CountryRepository;
import weinze.code.challenge.domain.repository.RunwayRepository;
import weinze.code.challenge.domain.services.InsightsService;

// TODO Parametrizar test para agregar más casos.
public class InsightsServiceTest {

    @InjectMocks
    private InsightsService target;

    @Mock
    private AirportRepository airportRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private RunwayRepository runwayRepository;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void topTenCountries() {

        final Airport a1 = airport(1L, "a");
        final Airport a2 = airport(2L, "a");
        final Airport a3 = airport(3L, "b");
        final Airport a4 = airport(4L, "c");
        final Airport a5 = airport(5L, "c");
        final Airport a6 = airport(6L, "c");

        final Country c1 = country("a");
        final Country c2 = country("b");
        final Country c3 = country("c");

        Mockito.when(this.airportRepository.findAll()).thenReturn(ImmutableList.of(a1, a2, a3, a4, a5, a6));
        Mockito.when(this.countryRepository.findAll()).thenReturn(ImmutableList.of(c1, c2, c3));
        Mockito.when(this.countryRepository.findByCode(Mockito.anyString())).thenCallRealMethod();

        final Map<Country, Long> result = target.topCountriesWithMoreAirports(2, false);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertNotNull(result.get(c1));
        assertEquals(result.get(c1), Long.valueOf(2));

        assertNotNull(result.get(c3));
        assertEquals(result.get(c3), Long.valueOf(3));

        assertNull(result.get(c2));
    }

    @Test
    public void topTenCountries2() {

        final Airport a1 = airport(1L, "a");
        final Airport a2 = airport(2L, "a");
        final Airport a3 = airport(3L, "b");
        final Airport a4 = airport(4L, "c");
        final Airport a5 = airport(5L, "c");
        final Airport a6 = airport(6L, "c");

        final Country c1 = country("a");
        final Country c2 = country("b");
        final Country c3 = country("c");

        Mockito.when(this.airportRepository.findAll()).thenReturn(ImmutableList.of(a1, a2, a3, a4, a5, a6));
        Mockito.when(this.countryRepository.findAll()).thenReturn(ImmutableList.of(c1, c2, c3));
        Mockito.when(this.countryRepository.findByCode(Mockito.anyString())).thenCallRealMethod();

        final Map<Country, Long> result = target.topCountriesWithMoreAirports(2, true);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertNotNull(result.get(c1));
        assertEquals(result.get(c1), Long.valueOf(2));

        assertNotNull(result.get(c2));
        assertEquals(result.get(c2), Long.valueOf(1));

        assertNull(result.get(c3));
    }

    @Test
    public void runwaysByCountry() {

        final Runway r1 = runway(1L, "AA", "");
        final Runway r2 = runway(2L, "AA", "");
        final Runway r3 = runway(2L, "BB", "");
        final Runway r4 = runway(2L, "BB", "");
        final Runway r5 = runway(3L, "CC", "");

        final Airport a1 = airport(1L, "a");
        final Airport a2 = airport(2L, "b");
        final Airport a3 = airport(3L, "a");

        final Country c1 = country("a");
        final Country c2 = country("b");

        Mockito.when(this.runwayRepository.findAll()).thenReturn(ImmutableList.of(r1, r2, r3, r4, r5));
        Mockito.when(this.airportRepository.findAll()).thenReturn(ImmutableList.of(a1, a2, a3));
        Mockito.when(this.countryRepository.findAll()).thenReturn(ImmutableList.of(c1, c2));
        Mockito.when(this.countryRepository.findByCode(Mockito.anyString())).thenCallRealMethod();

        Map<Country, List<String>> result = target.runwaysByCountry();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertNotNull(result.get(c1));
        assertEquals(result.get(c1).size(), 2);
        assertTrue(result.get(c1).contains("AA"));
        assertFalse(result.get(c1).contains("BB"));
        assertTrue(result.get(c1).contains("CC"));

        assertNotNull(result.get(c2));
        assertEquals(result.get(c2).size(), 2);
        assertTrue(result.get(c2).contains("AA"));
        assertTrue(result.get(c2).contains("BB"));
        assertFalse(result.get(c2).contains("CC"));
    }

    @Test
    public void topRunways() {
        final Runway r1 = runway(1L, "AA", "T1");
        final Runway r2 = runway(2L, "AA", "T2");
        final Runway r3 = runway(2L, "BB", "T2");
        final Runway r4 = runway(2L, "BB", "T2");
        final Runway r5 = runway(3L, "CC", "T3");
        final Runway r6 = runway(3L, "CC", "T3");
        final Runway r7 = runway(3L, "CC", "T3");
        final Runway r8 = runway(3L, "CC", "T4");

        Mockito.when(this.runwayRepository.findAll()).thenReturn(ImmutableList.of(r1, r2, r3, r4, r5, r6, r7, r8));

        final List<String> result = target.topRunways(2);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertFalse(result.contains("T1"));
        assertTrue(result.contains("T2"));
        assertTrue(result.contains("T3"));
        assertFalse(result.contains("T4"));

    }

    private Airport airport(Long id, String isoCountry) {
        final Airport airport = new Airport();
        airport.setId(id);
        airport.setIsoCountry(isoCountry);
        return airport;
    }

    private Country country(String code) {
        final Country country = new Country();
        country.setCode(code);
        return country;
    }

    private Runway runway(Long airport, String surface, String leIdent) {
        final Runway runway = new Runway();
        runway.setAirportRef(airport);
        runway.setSurface(surface);
        runway.setLeIdent(leIdent);
        return runway;
    }
}
