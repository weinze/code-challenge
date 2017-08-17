package weinze.code.challenge.domain.repository;

import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import weinze.code.challenge.domain.model.Country;

@Singleton
public class CountryRepository extends AbstractRepository<Country> {

    private List<Country> countries;

    @Inject
    public CountryRepository() {}

    @Override
    protected List<Country> cachedEntities() {
        return this.countries;
    }

    @Override
    protected void cachedEntities(List<Country> entities) {
        this.countries = entities;
    }

    public Country findByCode(String code) {
        return stream(findAll()).filter(c -> c.getCode().equals(code)).findFirst().orElse(null);
    }
}
