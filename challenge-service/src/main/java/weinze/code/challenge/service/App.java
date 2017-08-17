package weinze.code.challenge.service;

import static spark.Spark.after;
import static spark.Spark.staticFileLocation;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import weinze.code.challenge.service.controller.AirportController;
import weinze.code.challenge.service.controller.ApiController;
import weinze.code.challenge.service.controller.GenericController;
import weinze.code.challenge.service.controller.InsightsController;
import weinze.code.challenge.domain.readers.AbstractReader;
import weinze.code.challenge.domain.readers.AirportReader;
import weinze.code.challenge.domain.readers.CountryReader;
import weinze.code.challenge.domain.readers.RunwayReader;
import weinze.code.challenge.service.controller.QueryController;
import weinze.code.challenge.service.controller.ReportController;
import weinze.code.challenge.service.utils.PathsUtils;

public class App {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final String JSON = "application/json";

    private static final Injector injector = Guice.createInjector();
    private static final List<Class<? extends GenericController>> controllers = ImmutableList.of(ApiController.class, AirportController.class, InsightsController.class, QueryController.class, ReportController.class);
    private static final List<Class<? extends AbstractReader>> readers = ImmutableList.of(AirportReader.class, CountryReader.class, RunwayReader.class);

    public static void main(String[] args) throws Exception {
        try {
            staticFileLocation("webapp/public");

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

        } catch(Exception e) {
            // TODO
        }
    }
}