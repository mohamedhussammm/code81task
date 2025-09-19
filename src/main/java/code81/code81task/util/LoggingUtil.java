package code81.code81task.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggingUtil.class);
    
    public void logInfo(String message) {
        logger.info(message);
    }
    
    public void logWarning(String message) {
        logger.warn(message);
    }
    
    public void logError(String message) {
        logger.error(message);
    }
    
    public void logDebug(String message) {
        logger.debug(message);
    }
}