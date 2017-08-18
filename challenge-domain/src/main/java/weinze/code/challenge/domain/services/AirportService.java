package weinze.code.challenge.domain.services;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Airport;
import weinze.code.challenge.domain.model.insight.AirportFilter;
import weinze.code.challenge.domain.repository.AirportRepository;

public class AirportService {

    private AirportRepository repository;

    @Inject
    public AirportService(AirportRepository airportRepository) {
        this.repository = airportRepository;
    }

    public List<Airport> getAirports(AirportFilter filter) {
        return this.repository.findAll(filter);
    }
}
