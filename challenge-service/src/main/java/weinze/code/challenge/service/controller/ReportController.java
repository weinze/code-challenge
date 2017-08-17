package weinze.code.challenge.service.controller;

import static spark.Spark.get;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import weinze.code.challenge.domain.services.InsightsService;
import weinze.code.challenge.service.utils.PathsUtils;

public class ReportController implements GenericController {

    private InsightsService insightsService;

    @Inject
    public ReportController(InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    @Override
    public void initRoutes() {
        get(PathsUtils.getRootPath("report"), (req, res) -> this.report());
        get(PathsUtils.getRootPath("report/topCountries"), (req, res) -> this.topCountries());
    }

    public String report() {
        return map(new HashMap<>(), "report.vm");
    }

    public String topCountries() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("report", this.insightsService.topCountriesWithMoreAirports(10, false));
        return map(attributes, "topCountries.vm");
    }
}
