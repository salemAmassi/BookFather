package driver.bookstore;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
@Getter
// Operations logging class
// You can find logs in logs.log
public class CrudLogger {

    private static CrudLogger instance;

    private File logs;
    private Logger logger;
    private FileHandler fileHandler;

    // Returns singleton of CrudLogger class
    public static CrudLogger getInstance(){
        // If no singleton has been created yet, creates new static instance.
        if (instance ==null) {
            try {
                instance = new CrudLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // Private constructor can only be created from within class
    private CrudLogger() throws IOException {

        logs = new File("src/main/java/driver/bookstore/logs.log");
        fileHandler = new FileHandler(logs.getName(),true);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public void logSuccess(String message) throws IOException {
        FileWriter writer = new FileWriter(logs, true);
        writer.write("INFO "+ Date.from(Instant.now())+": "+message+"\n");
        writer.close();
    }

    public void logWarning(String message) throws IOException {
        FileWriter writer = new FileWriter(logs, true);
        writer.write("WARNING "+ Date.from(Instant.now())+": "+message+"\n");
        writer.close();
    }

    public void logDanger(String message) throws IOException {
        FileWriter writer = new FileWriter(logs, true);
        writer.write("DANGER "+ Date.from(Instant.now())+": "+message+"\n");
        writer.close();
    }
}
