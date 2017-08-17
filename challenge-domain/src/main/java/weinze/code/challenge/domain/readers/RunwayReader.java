package weinze.code.challenge.domain.readers;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Runway;
import weinze.code.challenge.domain.repository.RunwayRepository;

public class RunwayReader extends AbstractReader<Runway> {

    private static final String FILE_PATH = "data/runways.csv";

    private RunwayRepository repository;

    @Inject
    public RunwayReader(RunwayRepository runwayRepository) {
        this.repository = runwayRepository;
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected void saveList(List<Runway> list) {
        this.repository.saveAll(list);
    }

    @Override
    protected Runway map(String[] line) {
        final Runway runway = new Runway();
        runway.setId(Long.valueOf(line[0]));
        runway.setAirportRef(Long.valueOf(line[1]));
        runway.setAirportIdent(line[2]);
        runway.setSurface(line[5]);
        runway.setLeIdent(line[8]);
        return runway;
    }
}
