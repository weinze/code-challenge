package weinze.code.challenge.service.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;
import static weinze.code.challenge.service.controller.factory.RouteFactory.ok;
import static weinze.code.challenge.service.utils.JsonUtils.transformer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import spark.QueryParamsMap;
import spark.Route;
import weinze.code.challenge.domain.services.ApiService;
import weinze.code.challenge.service.utils.PathsUtils;

@Singleton
public class InsightsController implements GenericController {

    private ApiService apiService;

    @Inject
    public InsightsController(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void initRoutes() {
        path(PathsUtils.getApiPath("insights"), () -> {
            get("/topCountries", map((req, res) -> this.topCountries(req.queryMap())), transformer());
            get("/runwaysByCountry", map((req, res) -> this.runwaysByCountry()), transformer());
            get("/topTenRunways", map((req, res) -> this.topTenRunways()), transformer());
        });
    }

    /**
     * Top 10 de países con el mayor número de aeropuertos (acompañado dela cifra),
     * así como el top 10 de países con menos aeropuertos (de nuevo, con cifras).
     */
    private Route topCountries(QueryParamsMap query) {
        return ok(apiService.topCountriesWithMoreAirports(10, getOrder(query)));
    }

    /**
     * Tipos de pista por país (surface)
     */
    private Route runwaysByCountry() {
        return ok(apiService.runwaysByCountry());
    }

    /**
     * Top 10 de tipos de pistas más comunes (le_ident)
     */
    private Route topTenRunways() {
        return ok(apiService.topRunways(10));
    }

    private boolean getOrder(QueryParamsMap query) {
        if(query.hasKey("desc")) {
            return query.get("desc").booleanValue();
        }
        return false;
    }
}
