package weinze.code.challenge.domain.readers;

import static java.util.stream.Collectors.toList;
import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import weinze.code.challenge.domain.model.PersistentEntity;

public abstract class AbstractReader<T extends PersistentEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReader.class);

    protected List<T> readCSV(Reader file) throws IOException {
        final CSVReader reader = new CSVReader(file);
        return stream(reader.readAll()).skip(1).map(this::map).collect(toList());
    }

    public void init() {
        try(InputStream fileReader = this.getClass().getResourceAsStream(this.getFilePath())) {
            this.saveList(this.readCSV(new InputStreamReader(fileReader)));
        } catch(Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException(String.format("Cannot read file: %s", this.getFilePath()), e);
        }
    }

    protected abstract String getFilePath();

    protected abstract void saveList(List<T> list);

    protected abstract T map(String[] line);

    protected Long longValue(String str) {
        return StringUtils.isNotBlank(str) ? Long.valueOf(str) : null;
    }

    protected BigDecimal bigDecimalValue(String str) {
        return StringUtils.isNotBlank(str) ? new BigDecimal(str) : null;
    }

    protected Boolean booleanValue(String str) {
        if(StringUtils.isNotBlank(str)) {
            return str.equals("1") ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

}
