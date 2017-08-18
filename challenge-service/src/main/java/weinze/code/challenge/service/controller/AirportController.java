package weinze.code.challenge.service.controller;

import static java.util.Objects.nonNull;
import static spark.Spark.get;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;
import static weinze.code.challenge.service.controller.factory.RouteFactory.ok;
import static weinze.code.challenge.service.utils.JsonUtils.transformer;
import static weinze.code.challenge.service.utils.PathsUtils.getApiPath;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import spark.QueryParamsMap;
import spark.Route;
import weinze.code.challenge.domain.model.insight.AirportFilter;
import weinze.code.challenge.domain.services.AirportService;

@Singleton
public class AirportController implements GenericController {

    private AirportService service;

    @Inject
    public AirportController(AirportService airportService) {
        this.service = airportService;
    }

    @Override
    public void initRoutes() {
        get(getApiPath("airports"), map((req, res) -> this.getAirports(buildFilter(req.queryMap()))), transformer());
    }

    public Route getAirports(AirportFilter filter) {
        return ok(this.service.getAirports(filter));
    }

    private AirportFilter buildFilter(QueryParamsMap query) {
        final AirportFilter filter = new AirportFilter();
        if(nonNull(query)) {
            filter.setCountryName(query.get("country_name").value());
            filter.setCountryCode(query.get("country_code").value());
        }
        return filter;
    }
}
