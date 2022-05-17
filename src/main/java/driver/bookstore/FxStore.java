package driver.bookstore;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Our main driver of the project
 * */
public class FxStore extends Application {
    public static Stage primaryStage = new Stage();
    @Override
    public void start(Stage stage) throws IOException {
        File dashboard  = new File("src/main/resources/Main-Dashboard.fxml"); // Change path to start at a different fxml page
        Pane root = new FXMLLoader(dashboard.toURI().toURL()).load();
        Scene mainScene = new Scene(root);
        primaryStage = stage;
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Book Store");
        primaryStage.show();
        CrudLogger.getInstance().logSuccess("Test Log");
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() {
        Platform.exit();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
