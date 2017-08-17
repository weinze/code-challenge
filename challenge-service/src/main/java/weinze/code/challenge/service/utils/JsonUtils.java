package weinze.code.challenge.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import spark.ResponseTransformer;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson gson;

    static {
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private JsonUtils() {
    }

    public static ResponseTransformer transformer() {
        return JsonUtils::toJson;
    }

    public static String toJson(Object object) {
        try {
            return gson.toJson(object);
        } catch(JsonParseException e) {
            LOGGER.error("Error to parse object", e);
        }
        return null;
    }
}
