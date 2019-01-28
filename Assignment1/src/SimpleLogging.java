import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogging {
	public static void loggerFile(int validRows,int skippedRows, long totalExectime) {
		Handler consoleHandler = null;

		Handler fileHandler = null;
		Formatter simpleFormatter = null;
		Logger logger = Logger.getLogger("Main");
		// Creating consoleHandler and fileHandler
		consoleHandler = new ConsoleHandler();
		try {
			fileHandler = new FileHandler("./sampleLogfile.log");
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Assigning handlers to LOGGER object
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		// Setting levels to handlers and LOGGER
		logger.setLevel(Level.FINE);
		simpleFormatter = new SimpleFormatter();
		// Setting formatter to the handler
		fileHandler.setFormatter(simpleFormatter);
		logger.log(Level.FINE,"Total rows: "+validRows);
		logger.log(Level.FINE,"Skipped rows: "+skippedRows);
		logger.log(Level.FINE,"Total execution time: " +totalExectime+ " ms");
	}
}
