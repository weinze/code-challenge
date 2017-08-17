package weinze.code.challenge.service.controller;

import static spark.Spark.get;
import static weinze.code.challenge.service.controller.factory.RouteFactory.map;

import com.google.common.collect.Maps;
import weinze.code.challenge.service.utils.PathsUtils;

public class ApiController implements GenericController {

    @Override
    public void initRoutes() {
        get(PathsUtils.getApiPath(), (req, res) -> this.docs());
    }

    private String docs() {
        return map(Maps.newHashMap(), "docs.html");
    }
}
