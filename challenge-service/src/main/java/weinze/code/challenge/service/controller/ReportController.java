package weinze.code.challenge.service.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import spark.QueryParamsMap;
import weinze.code.challenge.domain.services.ApiService;
import weinze.code.challenge.service.utils.PathsUtils;

public class ReportController implements GenericController {

    private ApiService apiService;

    @Inject
    public ReportController(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void initRoutes() {
        path(PathsUtils.getRootPath("report"), () -> {
            get("", (req, res) -> this.report());
            get("/topCountries", (req, res) -> this.topCountries(req.queryMap()));
            get("/runwaysByCountry", (req, res) -> this.runwaysByCountry());
            get("/topRunways", (req, res) -> this.topRunways());
        });
    }

    private String report() {
        return map(new HashMap<>(), "report.vm");
    }

    private String topCountries(QueryParamsMap query) {
        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("report", this.apiService.topCountriesWithMoreAirports(10, getOrder(query)));
        return map(attributes, "topCountries.vm");
    }

    private String runwaysByCountry() {
        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("report", this.apiService.runwaysByCountry());
        return map(attributes, "runwaysByCountry.vm");
    }

    private String topRunways() {
        final Map<String, Object> attributes = new HashMap<>();
        attributes.put("report", this.apiService.topRunways(10));
        return map(attributes, "topRunways.vm");
    }

    private boolean getOrder(QueryParamsMap query) {
        if(query.hasKey("desc")) {
            return query.get("desc").booleanValue();
        }
        return false;
    }
}
