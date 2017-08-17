package weinze.code.challenge.domain.readers;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.repository.AirportRepository;

public class AirportReader extends AbstractReader<Airport> {

    private static final String FILE_PATH = "data/airports.csv";

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
        airport.setId(Long.valueOf(line[0]));
        airport.setIdent(line[1]);
        airport.setIsoCountry(line[8]);
        return airport;
    }
}
