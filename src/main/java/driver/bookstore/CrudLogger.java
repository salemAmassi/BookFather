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
public class CrudLogger  {
    private static CrudLogger instance;

    private File logs;
    private Logger logger;
    private FileHandler fileHandler;
    public static CrudLogger getInstance(){
        if (instance ==null) {
            try {
                instance = new CrudLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private CrudLogger() throws IOException {

        logs = new File("src/main/java/driver/bookstore/logs.txt");
        fileHandler = new FileHandler(logs.getName(),true);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
//        SimpleFormatter formatter = new SimpleFormatter();
//        fileHandler.setFormatter(formatter);
    }

}
