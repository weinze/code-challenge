package weinze.code.challenge.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static weinze.code.challenge.controller.factory.RouteFactory.map;
import static weinze.code.challenge.controller.factory.RouteFactory.ok;
import static weinze.code.challenge.domain.utils.JsonUtils.transformer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import spark.Route;
import weinze.code.challenge.domain.services.InsightsService;
import weinze.code.challenge.domain.utils.PathsUtils;

@Singleton
public class InsightsController implements GenericController {

    private InsightsService insightsService;

    @Inject
    public InsightsController(InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    @Override
    public void initRoutes() {
        path(PathsUtils.getApiPath("insights"), () -> {
            get("/test", map((req, res) -> this.topTenAsc()), transformer());
            get("/test2", map((req, res) -> this.topTenDesc()), transformer());
            get("/test3", map((req, res) -> this.runwaysByCountry()), transformer());
            get("/test4", map((req, res) -> this.topTenRunways()), transformer());
        });
    }

    // TODO TopTen con mismo path, orden en query
    private Route topTenAsc() {
        return ok(insightsService.topCountriesWithMoreAirports(10, false));
    }

    private Route topTenDesc() {
        return ok(insightsService.topCountriesWithMoreAirports(10, true));
    }

    /**
     * Tipos de pista por país (surface)
     */
    private Route runwaysByCountry() {
        return ok(insightsService.runwaysByCountry());
    }

    /**
     * Top 10 de tipos de pistas más comunes (le_ident)
     */
    private Route topTenRunways() {
        return ok(insightsService.topRunways(10));
    }
}
