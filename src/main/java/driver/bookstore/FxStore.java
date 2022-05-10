package driver.bookstore;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;

/**
 * Our main driver of the project
 * */
public class FxStore extends Application {
    public static Stage primaryStage;
    //application context
//    private ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage stage) throws IOException {
//        applicationContext.publishEvent(new StageReadyEvent(stage));
        File dashboard  = new File("src/main/resources/AddBook.fxml");
        Pane root = new FXMLLoader(dashboard.toURI().toURL()).load();
        Scene mainScene = new Scene(root);
        primaryStage = stage;
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Book Store");
        primaryStage.show();
    }


    @Override
    public void init() throws Exception {
super.init();
    }

    @Override
    public void stop() {
//       applicationContext.close();
        Platform.exit();
    }
    //to give access to all listeners into the primary stage!
    public static void main(String[] args) {
        launch(args);
    }
}
