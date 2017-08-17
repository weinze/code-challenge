package weinze.code.challenge.domain.utils;

public class PathsUtils {

    private static final String APP_PATH = "code-challenge";
    private static final String API_PATH = "api";
    private static final String SLASH = "/";

    public static String getRootPath() {
        return APP_PATH;
    }

    public static String getRootPath(String path) {
        return getRootPath().concat(SLASH).concat(path);
    }

    public static String getApiPath() {
        return getRootPath(API_PATH);
    }

    public static String getApiPath(String path) {
        return getApiPath().concat(SLASH).concat(path);
    }

    public static String getRelativePath(String path) {
        return SLASH.concat(path);
    }

}
