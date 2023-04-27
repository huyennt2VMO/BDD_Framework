package utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
	public static Logger getLogger() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		
		String className = stackTrace[2].getClassName();
		return LoggerFactory.getLogger(className);
	}
}
