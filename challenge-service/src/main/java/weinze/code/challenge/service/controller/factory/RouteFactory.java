package weinze.code.challenge.service.controller.factory;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.eclipse.jetty.http.HttpStatus.OK_200;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class RouteFactory {

    public static Route ok(Object body) {
        return response(OK_200, body);
    }

    public static Route fail(Object body) {
        return response(BAD_REQUEST_400, body);
    }

    public static Route notFound(Object body) {
        return response(NOT_FOUND_404, body);
    }

    public static Route error(Object body) {
        return response(INTERNAL_SERVER_ERROR_500, body);
    }

    public static Route response(int code, Object body) {
        return (req, res) -> {
            res.status(code);
            return body;
        };
    }

    public static String map(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, "webapp/views/" + templatePath));
    }

    public static Route map(Converter c) {
        return (req, res) -> c.convert(req, res).handle(req, res);
    }

    public interface Converter {
        Route convert(Request req, Response res);
    }
}
