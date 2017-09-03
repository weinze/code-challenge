package weinze.code.challenge.domain.readers;

import java.util.List;

import com.google.inject.Inject;
import weinze.code.challenge.domain.model.Country;
import weinze.code.challenge.domain.repository.CountryRepository;

public class CountryReader extends AbstractReader<Country> {

    private static final String FILE_PATH = "/data/countries.csv";

    private CountryRepository repository;

    @Inject
    public CountryReader(CountryRepository countryRepository) {
        this.repository = countryRepository;
    }

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected void saveList(List<Country> list) {
        this.repository.saveAll(list);
    }

    @Override
    protected Country map(String[] line) {
        final Country country = new Country();
        country.setId(longValue(line[0]));
        country.setCode(line[1]);
        country.setName(line[2]);
        country.setContinent(line[3]);
        country.setWikipediaLink(line[4]);
        country.setKeywords(line[5]);
        return country;
    }
}
