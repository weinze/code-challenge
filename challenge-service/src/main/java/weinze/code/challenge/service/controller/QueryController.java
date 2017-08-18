package weinze.code.challenge.service.controller;

import static spark.Spark.get;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import spark.QueryParamsMap;
import weinze.code.challenge.domain.model.insight.AirportFilter;
import weinze.code.challenge.domain.services.AirportService;
import weinze.code.challenge.service.utils.PathsUtils;

public class QueryController implements GenericController {

    private AirportService airportService;

    @Inject
    public QueryController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public void initRoutes() {
        get(PathsUtils.getRootPath("query"), (req, res) -> this.query(req.queryMap()));
    }

    private String query(QueryParamsMap query) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("query", this.airportService.getAirports(buildFilter(query)));
        return map(attributes, "query.vm");
    }

    private AirportFilter buildFilter(QueryParamsMap query) {
        final AirportFilter filter = new AirportFilter();
        final QueryParamsMap country = query.get("country");
        if(country.hasValue()) {
            if(country.value().equals("name")) {
                filter.setCountryName(query.get("value").value());
            } else if(country.value().equals("code")) {
                filter.setCountryCode(query.get("value").value());
            }
        }
        return filter;
    }
}
