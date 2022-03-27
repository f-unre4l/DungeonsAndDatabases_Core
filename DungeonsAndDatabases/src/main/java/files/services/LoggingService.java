package files.services;

import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    public static String apiDocumentation = "Please look at the API Docu for more information: http://localhost:8080/swagger-ui/index.html#/";

    public static void logError(Logger logger, Exception e) {
        logger.error("Something went wrong. " + apiDocumentation, e);
    }

    public static void logError(Logger logger, Exception e, String message) {
        logger.error(message + " " + apiDocumentation, e);
    }

    public static <T> void logInfoSuccess(Logger logger, HttpMethod httpMethod, T object) {
        logger.info("successful " + httpMethod.name() + " of: " + object);
    }

    public static void logWarnRequest(Logger logger, HttpStatus status) {
        logger.warn("Request went wrong. StatusCode: " + status);
    }

}
