package weinze.code.challenge.service.factory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import weinze.code.challenge.domain.readers.AbstractReader;
import weinze.code.challenge.domain.readers.AirportReader;
import weinze.code.challenge.domain.readers.CountryReader;
import weinze.code.challenge.domain.readers.RunwayReader;
import weinze.code.challenge.service.controller.AirportController;
import weinze.code.challenge.service.controller.ApiController;
import weinze.code.challenge.service.controller.GenericController;
import weinze.code.challenge.service.controller.InsightsController;
import weinze.code.challenge.service.controller.QueryController;
import weinze.code.challenge.service.controller.ReportController;

public class AppFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppFactory.class);

    private static final Injector injector = Guice.createInjector();

    private static final List<Class<? extends AbstractReader>> READERS = ImmutableList.of(AirportReader.class, CountryReader.class, RunwayReader.class);
    private static final List<Class<? extends GenericController>> CONTROLLERS = ImmutableList
            .of(ApiController.class, AirportController.class, InsightsController.class, QueryController.class, ReportController.class);

    public static void initReaders() {
        READERS.forEach(clazz -> {
            LOGGER.debug("Init reader for class {}", clazz.getSimpleName());
            AbstractReader reader = injector.getInstance(clazz);
            reader.init();
        });

    }

    public static void initRoutes() {
        CONTROLLERS.forEach(clazz -> {
            LOGGER.debug("Add routes for class {}", clazz.getSimpleName());
            GenericController controller = injector.getInstance(clazz);
            controller.initRoutes();
        });
    }
}
