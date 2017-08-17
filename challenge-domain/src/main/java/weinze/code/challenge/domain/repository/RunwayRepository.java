package weinze.code.challenge.domain.repository;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import weinze.code.challenge.domain.model.Runway;

@Singleton
public class RunwayRepository extends AbstractRepository<Runway> {

    private List<Runway> runways;

    @Inject
    public RunwayRepository() {}

    @Override
    protected List<Runway> cachedEntities() {
        return this.runways;
    }

    @Override
    protected void cachedEntities(List<Runway> entities) {
        this.runways = entities;
    }
}
