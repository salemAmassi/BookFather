package driver.bookstore;
import driver.bookstore.Category.CategoryFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import driver.bookstore.FxStore.StageReadyEvent;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * A class to set up the stage when it is ready (when we run the ApplicationContext
 * We will link to our view files (.fxml)
 * */
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @SneakyThrows
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        File dashboard  = new File("src/main/resources/AddBook.fxml");
        Pane root = new FXMLLoader(dashboard.toURI().toURL()).load();
        Scene mainScene = new Scene(root);
        Stage stage =  event.getStage();
        stage.setScene(mainScene);
        stage.setTitle("Book Store");
        stage.show();
    }
}
