package weinze.code.challenge;

import static spark.Spark.after;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.servlet.SparkApplication;
import weinze.code.challenge.controller.AirportController;
import weinze.code.challenge.controller.GenericController;
import weinze.code.challenge.controller.InsightsController;
import weinze.code.challenge.domain.readers.AbstractReader;
import weinze.code.challenge.domain.readers.AirportReader;
import weinze.code.challenge.domain.readers.CountryReader;
import weinze.code.challenge.domain.readers.RunwayReader;
import weinze.code.challenge.domain.utils.PathsUtils;

public class App implements SparkApplication {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final String JSON = "application/json";

    private static final Injector injector = Guice.createInjector();
    private static final List<Class<? extends GenericController>> controllers = ImmutableList.of(AirportController.class, InsightsController.class);
    private static final List<Class<? extends AbstractReader>> readers = ImmutableList.of(AirportReader.class, CountryReader.class, RunwayReader.class);

    public static void main(String[] args) throws Exception {
        try {
            new App().init();
        } catch(Exception e) {
            // TODO
        }
    }

    @Override
    public void init() {

        after(PathsUtils.getApiPath("*"), (req, res) -> res.type(JSON));

        // TODO Add in some factory
        readers.forEach(clazz -> {
            LOGGER.debug("Init reader for class {}", clazz.getSimpleName());
            AbstractReader reader = injector.getInstance(clazz);
            reader.init();
        });

        controllers.forEach(clazz -> {
            LOGGER.debug("Add routes for class {}", clazz.getSimpleName());
            GenericController controller = injector.getInstance(clazz);
            controller.initRoutes();
        });
    }
}
