package weinze.code.challenge.service;

import static spark.Spark.after;
import static spark.Spark.staticFileLocation;

import org.slf4j.LoggerFactory;

import weinze.code.challenge.service.factory.AppFactory;
import weinze.code.challenge.service.utils.PathsUtils;

public class App {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final String JSON = "application/json";

    public static void main(String[] args) throws Exception {
        try {
            LOGGER.info("Start application...");

            staticFileLocation("webapp/public");

            after(PathsUtils.getApiPath("*"), (req, res) -> res.type(JSON));

            AppFactory.initReaders();
            AppFactory.initRoutes();

            LOGGER.info("Application started");
        } catch(Exception e) {
            LOGGER.error("Error on startup", e);
        }
    }
}
