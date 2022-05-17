package driver.bookstore;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
@Getter
// Operations logging class
// You can find logs in logs.log
public class CrudLogger  {

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
//        SimpleFormatter formatter = new SimpleFormatter();
//        fileHandler.setFormatter(formatter);
    }

    // TODO: Create log methods for CRUD operations.
}
