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
        runway.setId(longValue(line[0]));
        runway.setAirportRef(longValue(line[1]));
        runway.setAirportIdent(line[2]);
        runway.setLengthFt(longValue(line[3]));
        runway.setWidthFt(longValue(line[4]));
        runway.setSurface(line[5]);
        runway.setLighted(booleanValue(line[6]));
        runway.setClosed(booleanValue(line[7]));
        runway.setLeIdent(line[8]);
        runway.setLeLatitudeDeg(bigDecimalValue(line[9]));
        runway.setLeLongitudeDeg(bigDecimalValue(line[10]));
        runway.setLeElevationFt(bigDecimalValue(line[11]));
        runway.setLeHeadingDegT(bigDecimalValue(line[12]));
        runway.setLeDisplacedThresholdFt(bigDecimalValue(line[13]));
        runway.setHeIdent(line[14]);
        runway.setHeLatitudeDeg(bigDecimalValue(line[15]));
        runway.setHeLongitudeDeg(bigDecimalValue(line[16]));
        runway.setHeElevationFt(bigDecimalValue(line[17]));
        runway.setHeHeadingDegT(bigDecimalValue(line[18]));
        runway.setHeDisplacedThresholdFt(bigDecimalValue(line[19]));
        return runway;
    }
}
