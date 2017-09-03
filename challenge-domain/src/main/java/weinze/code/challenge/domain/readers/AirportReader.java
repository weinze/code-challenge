package weinze.code.challenge.domain.readers;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.repository.AirportRepository;

public class AirportReader extends AbstractReader<Airport> {

    private static final String FILE_PATH = "/data/airports.csv";

    private AirportRepository repository;

    @Inject
    public AirportReader(AirportRepository airportRepository) {
        this.repository = airportRepository;
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected void saveList(List<Airport> list) {
        this.repository.saveAll(list);
    }

    @Override
    protected Airport map(String[] line) {
        final Airport airport = new Airport();
        airport.setId(longValue(line[0]));
        airport.setIdent(line[1]);
        airport.setType(line[2]);
        airport.setName(line[3]);
        airport.setLatitudeDeg(bigDecimalValue(line[4]));
        airport.setLongitudeDeg(bigDecimalValue(line[5]));
        airport.setElevationFt(longValue(line[6]));
        airport.setContinent(line[7]);
        airport.setIsoCountry(line[8]);
        airport.setIsoRegion(line[9]);
        airport.setMunicipality(line[10]);
        airport.setScheduledService(line[11]);
        airport.setGpsCode(line[12]);
        airport.setIataCode(line[13]);
        airport.setLocalCode(line[14]);
        airport.setHomeLink(line[15]);
        airport.setWikipediaLink(line[16]);
        airport.setKeywords(line[17]);
        return airport;
    }
}
